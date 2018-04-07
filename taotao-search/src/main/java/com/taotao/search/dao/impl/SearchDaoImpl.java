package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package com.taotao.search.dao.impl
 * @User 12428
 * @Date 2018/3/30 19:47
 *
 * solr查询结果
 */
@Repository
public class SearchDaoImpl implements SearchDao {
    @Autowired
    private SolrServer solrServer;
    @Override
    public SearchResult search(SolrQuery query) throws SolrServerException {
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询列表
        SolrDocumentList results = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        //遍历
        for (SolrDocument document : results) {
            //创建SearchItem对象
            SearchItem item = new SearchItem();
            item.setId((String) document.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String itemTitle = "";
            if(list!=null && list.size()>0){
                //取高亮后的结果
                itemTitle = list.get(0);
            } else{
                itemTitle = (String) document.get("item_title");
            }
            item.setTitle(itemTitle);
            item.setSell_point((String) document.get("item_sell_point"));
            item.setPrice((Long) document.get("item_price"));
            item.setImage((String) document.get("item_image"));
            item.setCategory_name((String) document.get("item_category_name"));
            item.setItem_desc((String) document.get("item_desc"));
            //添加到列表
            itemList.add(item);

        }
        SearchResult result = new SearchResult();
        result.setItemList(itemList);
        //查询结果总数量
        result.setRecoreCount(results.getNumFound());
        return result;
    }
}
