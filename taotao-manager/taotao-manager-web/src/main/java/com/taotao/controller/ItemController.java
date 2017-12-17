package com.taotao.controller;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item")
    @ResponseBody
    private TbItem getItemById(Long itemId){
        System.out.println("邱新磊");
        TbItem item = itemService.getItemById(itemId);
        return item;
    }
}
