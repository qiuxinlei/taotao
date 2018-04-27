package com.taotao.protal.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.protal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package com.taotao.protal.controller
 * @User 12428
 * @Date 2018/4/27 23:08
 */

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num,
                          HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = cartService.addCart(itemId, num, request, response);
        return "cartSuccess";
    }
}
