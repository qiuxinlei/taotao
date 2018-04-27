package com.taotao.protal.service;

import com.taotao.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package com.taotao.protal.service
 * @User 12428
 * @Date 2018/4/25 23:09
 */

public interface CartService {
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
}
