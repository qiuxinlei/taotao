package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理Controller
 * @Package com.taotao.rest.controller
 * @User 12428
 * @Date 2018/4/10 22:46
 */

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/base/{itemId}")
    @ResponseBody
    public TaotaoResult getItemById(@PathVariable Long itemId){
        try {
            TbItem item = itemService.getItemById(itemId);
            return TaotaoResult.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescById(@PathVariable Long itemId){
        try {
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            return TaotaoResult.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }
    /**
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParamById(@PathVariable Long itemId){
        try {
            TbItemParamItem itemParamItem = itemService.getItemParamById(itemId);
            return TaotaoResult.ok(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }
}
