package com.taotao.protal.controller;

import com.taotao.protal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package com.taotao.protal.controller
 * @User 12428
 * @Date 2018/3/20 16:12
 */

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;
    @RequestMapping("/index")
    public String showIndex(Model model){
        //去打广告位内容
        String json = contentService.getAd1List();
        model.addAttribute("ad1",json);
        return "index";
    }

    @RequestMapping(value = "/posttest", method = RequestMethod.POST)
    @ResponseBody
    public String postTest(@RequestBody String name,@RequestBody String pass){
        System.out.println(name);
        System.out.println(pass);
        return "OK";
    }

}
