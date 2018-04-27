package com.taotao.protal.service;

import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package com.taotao.protal.service
 * @User 12428
 * @Date 2018/4/22 10:49
 */

public interface UserService {

    TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
