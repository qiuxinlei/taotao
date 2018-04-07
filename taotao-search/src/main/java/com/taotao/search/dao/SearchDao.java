package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * @Package com.taotao.search.dao
 * @User 12428
 * @Date 2018/3/30 19:46
 */

public interface SearchDao {
    SearchResult search(SolrQuery query) throws SolrServerException;

}
