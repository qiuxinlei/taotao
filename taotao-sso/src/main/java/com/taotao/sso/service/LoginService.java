package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package com.taotao.sso.service
 * @User 12428
 * @Date 2018/4/20 22:57
 */

public interface LoginService {

    TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult getUserByToken(String token);
}
