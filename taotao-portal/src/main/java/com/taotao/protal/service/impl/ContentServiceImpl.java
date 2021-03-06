package com.taotao.protal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.protal.pojo.AdNode;
import com.taotao.protal.service.ContentService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/3/23 10:00
 */
@Service
public class ContentServiceImpl implements ContentService {
    /*
    获得广告位的内容
    * */
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_BASE_URL}")
    private String REST_CONTENT_BASE_URL;
    @Value("${REST_CONTET_AD1_CID}")
    private String REST_CONTET_AD1_CID;


    @Override
    public String getAd1List() {
        //调用服务获得数据
        String json = HttpClientUtil.get(REST_BASE_URL + REST_CONTENT_BASE_URL + REST_CONTET_AD1_CID);
        //把json转换成java对行啊
        TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);
        //去data属性，内容列表
        List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
        //把内容列表转换成AdNode列表
        List<AdNode> resultList = new ArrayList<>();
        for ( TbContent tbContent : contentList ) {
            AdNode node = new AdNode();
            node.setHeight(240);
            node.setWidth(670);
            node.setSrc(tbContent.getPic());
            node.setHeightB(240);
            node.setWidthB(550);
            node.setSrcB(tbContent.getPic2());
            node.setAlt(tbContent.getSubTitle());
            node.setHref(tbContent.getUrl());
            resultList.add(node);
        }
        //需要报resultList转成json数据
        String resultJson = JsonUtils.objectToJson(resultList);
        return resultJson;
    }
}
