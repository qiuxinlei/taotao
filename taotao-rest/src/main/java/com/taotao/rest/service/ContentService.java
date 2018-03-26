package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Package com.taotao.rest.service
 * @User 12428
 * @Date 2018/3/23 9:35
 */

public interface ContentService {

    List<TbContent> getContentList(Long cid);
    //同步redis
    TaotaoResult syncContent(Long cid);
}
