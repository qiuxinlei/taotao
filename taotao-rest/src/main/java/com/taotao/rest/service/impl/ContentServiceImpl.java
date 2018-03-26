package com.taotao.rest.service.impl;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ContentService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.taotao.rest.service.impl
 * @User 12428
 * @Date 2018/3/23 9:36
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;
    @Override
    public List<TbContent> getContentList(Long cid) {
        //查询数据库之前，查看缓存
        try {
            String json = jedisClient.hget(REDIS_CONTENT_KEY, cid + "");
            if(!StringUtils.isBlank(json)){
                //将json转成list
                List<TbContent> list = JsonUtils.jsonToList(json,TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据cid查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        System.out.println("输出这条结果，说明查的是数据库的数据！！");
        //返回结果之前，向缓存中添加数据
        try {
            //为了规范key，可以使用hash
            //第一一个保存内容的key，hash中的每个项就是cid
            //value四list，需要把list转成json数据
            jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TaotaoResult syncContent(Long cid) {
        Long result = jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
        return TaotaoResult.ok();
    }
}
