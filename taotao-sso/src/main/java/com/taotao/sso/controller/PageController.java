package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package com.taotao.sso.controller
 * @User 12428
 * @Date 2018/4/22 10:24
 */
@Controller
public class PageController {


    @RequestMapping("/page/login")
     public String showLogin(String redirectURL, Model model){
        //需要把参数传递给jsp
        model.addAttribute("redirect",redirectURL);
        return "login";
    }

    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }
}
