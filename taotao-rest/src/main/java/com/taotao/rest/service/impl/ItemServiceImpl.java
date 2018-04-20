package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理Service
 * @Package com.taotao.rest.service.impl
 * @User 12428
 * @Date 2018/4/9 21:43
 */

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Autowired
    private JedisClient jedisClient;
//    #内容缓存在redis中的key
//    REDIS_CONTENT_KEY = REDIS_CONTENT_KEY
//    #redis中商品信息key
//    REDIS_ITEM_KEY=REDIS_ITEM
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value(("${ITEM_EXPIRE_SECOND}"))
    private Integer ITEM_EXPIRE_SECOND;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;
    @Override
    public TbItem getItemById(Long itemId) {
        String key = REDIS_ITEM_KEY + ":"+itemId+":" +ITEM_BASE_INFO_KEY;
        //查询缓存，如果有缓存，则返回，没有则查询关系型数据库
        try {
            String json = jedisClient.get(key);
            //判断数据是否存在
            if(StringUtils.isNotBlank(json)){
                //把json数据成java对象
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                return tbItem;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //向redis中添加缓存
        //添加缓存原则是不能影响正常的业务逻辑
        try {
            // 向redis中添加缓存
            jedisClient.set(key,
                    JsonUtils.objectToJson(item));
            // 设置key的过期时间
            jedisClient.expire(key,ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        String key = REDIS_ITEM_KEY+":"+itemId + ":" +ITEM_DESC_KEY;
        //查询缓存，如果有缓存，则返回，没有则查询关系型数据库
        try {
            String json = jedisClient.get(key);
            //判断数据是否存在
            if(StringUtils.isNotBlank(json)){
                //把json数据成java对象
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return itemDesc;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //根据商品id查询商品详情
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        //向redis中添加缓存
        //添加缓存原则是不能影响正常的业务逻辑
        try {
            // 向redis中添加缓存
            jedisClient.set(key,
                    JsonUtils.objectToJson(itemDesc));
            // 设置key的过期时间
            jedisClient.expire(key,ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }

    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        String key = REDIS_ITEM_KEY+":"+itemId + ":" +ITEM_PARAM_KEY;
        //查询缓存，如果有缓存，则返回，没有则查询关系型数据库
        try {
            String json = jedisClient.get(key);
            //判断数据是否存在
            if(StringUtils.isNotBlank(json)){
                //把json数据成java对象
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return itemParamItem;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //更具商品id查规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        //取规格参数
        if(list != null && list.size() > 0){
            TbItemParamItem itemParamItem = list.get(0);
            //向redis中添加缓存
            //添加缓存原则是不能影响正常的业务逻辑
            try {
                // 向redis中添加缓存
                jedisClient.set(key,
                        JsonUtils.objectToJson(itemParamItem));
                // 设置key的过期时间
                jedisClient.expire(key,ITEM_EXPIRE_SECOND);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemParamItem;
        }
        return null;
    }
}
