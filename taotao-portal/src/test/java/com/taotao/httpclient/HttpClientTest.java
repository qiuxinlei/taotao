package com.taotao.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.com.taotao.httpclient
 * @User 12428
 * @Date 2018/3/22 22:43
 */


public class HttpClientTest {

    @Test
    public void testHttpGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.itheima.com");
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity);
        System.out.println(html);
        response.close();
        httpClient.close();

    }

    @Test
    public void  testHttpPost() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8082/posttest.html");
        List<NameValuePair> formList = new ArrayList<>();
        formList.add(new BasicNameValuePair("name","曲乐欧"));
        formList.add(new BasicNameValuePair("pass","quleou"));
        StringEntity entity = new UrlEncodedFormEntity(formList,"utf-8");
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        System.out.println(result);
        response.close();
        httpClient.close();

    }
}
