package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * @Package com.taotao.search.service
 * @User 12428
 * @Date 2018/4/2 21:27
 */

public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws SolrServerException;
}
