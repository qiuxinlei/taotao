package com.taotao.protal.service;

import com.taotao.protal.pojo.SearchResult;

/**
 * @Package com.taotao.protal.service
 * @User 12428
 * @Date 2018/4/2 22:47
 */

public interface SearchService {
    SearchResult search(String keyword, int page, int rows);
}
