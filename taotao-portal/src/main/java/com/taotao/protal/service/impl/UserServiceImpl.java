package com.taotao.protal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.protal.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理Service
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/4/22 10:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从cookie中取token
            String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
            // 判断token是否有值
            if (StringUtils.isBlank(token)){
                return null;
            }
            // 调用sso的服务查询用户信息
            String url = SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token;
            String json = HttpClientUtil.get(url);
            // json数据转成java对象
            TaotaoResult result = TaotaoResult.format(json);
            if (result.getStatus() != 200){
                return null;
            }
            //取用户对象
            result = TaotaoResult.formatToPojo(json, TbUser.class);
            TbUser user = (TbUser) result.getData();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
