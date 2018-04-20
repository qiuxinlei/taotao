package com.taotao.protal.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.protal.service.StaticPageService;
import com.taotao.utils.ExceptionUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Package com.taotao.protal.controller
 * @User 12428
 * @Date 2018/4/18 0:02
 */

@Controller
public class StaticPageController {
    @Autowired
    private StaticPageService staticPageService;

    @RequestMapping(value = "/gen/item/{itemId}")
    @ResponseBody
    public TaotaoResult genItemPage(@PathVariable Long itemId){
        try {
            TaotaoResult result = staticPageService.genItemHtml(itemId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
