package com.taotao.sso.service.impl;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.component.JedisClient;
import com.taotao.sso.service.LoginService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @Package com.taotao.sso.service.impl
 * @User 12428
 * @Date 2018/4/20 22:58
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;


    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //校验用户名密码是否正确
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        // 校验用户
        criteria.andUsernameEqualTo(username);
        // 校验密码
        criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
        List<TbUser> list = userMapper.selectByExample(example);
        // 取用户信息
        if(list == null || list.size() == 0){
            return TaotaoResult.build(400,"用户名或密码错误");
        }
        TbUser user = list.get(0);
        // 登录成功
        // 生成token
        String token = UUID.randomUUID().toString();
        // 把用户信息写入redis
        // key：REDIS_SESSION:{TOKEN}
        //value：user转json
        String key = REDIS_SESSION_KEY + ":" + token;
        user.setPassword(null);
        String value = JsonUtils.objectToJson(user);
        jedisClient.set(key, value);
        // 设置session的过期时间
        jedisClient.expire(key, SESSION_EXPIRE);
        //写cookie
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        // 根据token取用户信息
        String key = REDIS_SESSION_KEY + ":" + token;
        String json = jedisClient.get(key);
        // 判断是否查询到结果
        if(StringUtils.isBlank(json)){
            return TaotaoResult.build(400, "用户session已经过期");
        }
        // 把json转成java对象
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        // 更新session的过期时间
        jedisClient.expire(key, SESSION_EXPIRE);
        return TaotaoResult.ok(user);
    }
}
