package com.taotao.protal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.protal.pojo.SearchResult;
import com.taotao.protal.service.SearchService;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/4/2 22:48
 */

@Service
public class SearchServiceImpl implements SearchService{

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String keyword, int page, int rows) {
        //调用服务查询商品列表
        Map<String, String> param = new HashMap<>();
        //设置参数
        param.put("keyword",keyword);
        param.put("page",page+"");
        param.put("rows",rows+"");
        String json = HttpClientUtil.get(SEARCH_BASE_URL, param);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        //取返回的结果
        SearchResult searchResult = (SearchResult) taotaoResult.getData();
        return searchResult;
    }
}
