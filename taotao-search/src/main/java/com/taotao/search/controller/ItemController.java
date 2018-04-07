package com.taotao.search.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package com.taotao.search.controller
 * @User 12428
 * @Date 2018/3/30 17:32
 */

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/importAll")
    @ResponseBody
    public TaotaoResult importAll(){
        try {
            TaotaoResult result = itemService.importItems();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
