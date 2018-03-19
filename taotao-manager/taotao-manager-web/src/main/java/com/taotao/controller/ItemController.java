package com.taotao.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId){
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping(value = "/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item,String desc,String itemParams){

        TaotaoResult result = itemService.createItem(item, desc,itemParams);
        return result;
    }
    @RequestMapping(value = "/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult selectItemParam(Integer page,Integer rows){
        EasyUIDataGridResult paramList = itemService.getParamList(page,rows);
        System.out.println(paramList.getRows().size());
        return paramList;
    }

    @RequestMapping("/page/item/{itemId}")
    public String showItemParam(@PathVariable Long itemId, Model model){
        String html = itemService.getItemParamHtml(itemId);
        model.addAttribute("myHtml",html);
        return "itemparam";
    }
}
