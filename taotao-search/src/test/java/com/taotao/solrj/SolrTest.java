package com.taotao.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @Package com.taotao.solrj
 * @User 12428
 * @Date 2018/3/30 12:55
 */

public class SolrTest {

    @Test
    public void testSolrJ() throws IOException, SolrServerException {
        //创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.241.162:8080/solr/collection1");
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //添加域
        document.addField("id","solrtest01");
        document.addField("item_title","测试商品");
        document.addField("item_sell_point","卖点");
        //调教索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    @Test
    public void testQuery() throws SolrServerException {
        //创建连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.241.162:8180/solr/collection2");
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        //执行查询
        QueryResponse response = solrServer.query(solrQuery);
        //取查询结果
        SolrDocumentList results = response.getResults();
        for (SolrDocument solrDocument : results) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
        }
    }
    /*
    * solr集群
    * */
    @Test
    public void testSolrCloud() throws IOException, SolrServerException {
        //创建solrServer
        CloudSolrServer solrServer = new CloudSolrServer("192.168.241.162:2181,192.168.241.162:2182,192.168.241.162:2183");
        //设置默认的collection
        solrServer.setDefaultCollection("collection2");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test01");
        document.addField("item_title","title1");
        document.addField("item_sell_point","集群卖点");
        //添加文档
        solrServer.add(document);
        //提交
        solrServer.commit();
    }
    /*
    * solr集群测试查询
    * */
    @Test
    public void testSolrCloudQuery() throws SolrServerException {
        CloudSolrServer solrServer = new CloudSolrServer("192.168.241.162:2181,192.168.241.162:2182,192.168.241.162:2183");
        solrServer.setDefaultCollection("collection2");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument document : results ) {
            System.out.println(document.get("id"));
        }
    }
}
