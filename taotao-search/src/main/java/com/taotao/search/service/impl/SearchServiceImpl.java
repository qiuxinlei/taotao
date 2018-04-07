package com.taotao.search.service.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.taotao.search.service.impl
 * @User 12428
 * @Date 2018/4/2 21:28
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws SolrServerException {
        //创建查询条件
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        solrQuery.setQuery(queryString);
        //设置分页条件
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //设置搜索域
        solrQuery.set("df","item_title");
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        solrQuery.setHighlightSimplePost("</font>");
        //执行查询
        SearchResult searchResult = searchDao.search(solrQuery);
        //计算总页数
        Long recoreCount = searchResult.getRecoreCount();
        Long pageCount = (Long) ((recoreCount+rows-1)/rows);
        searchResult.setPageCount(pageCount);
        return searchResult;
    }
}
