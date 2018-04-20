package com.taotao.protal.controller;

import com.taotao.pojo.TbItem;
import com.taotao.protal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;
import java.awt.*;

/**
 * 展示商品详情页面
 * @Package com.taotao.protal.controller
 * @User 12428
 * @Date 2018/4/16 20:04
 */

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId,Model model){
        TbItem item = itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){
        String desc = itemService.getItemDescById(itemId);
        return desc;
    }
    /*
    用这个测试，其他的基本没有
    http://localhost:8082/item/1458729469.html
    */
    @RequestMapping(value = "/item/param/{itemId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId){
        String param = itemService.getItemParamById(itemId);
        return param;
    }
}
