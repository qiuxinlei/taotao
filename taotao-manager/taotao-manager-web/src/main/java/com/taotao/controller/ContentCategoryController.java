package com.taotao.controller;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理Controller
 * @Package com.taotao.controller
 * @User 12428
 * @Date 2018/3/21 19:47
 */

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCatgoryService contentCatgoryService;
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId ){
        List<EasyUITreeNode> result = contentCatgoryService.getContentCatList(parentId);
        return result;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createNode(Long parentId,String name){
        TaotaoResult result = contentCatgoryService.insertCategory(parentId, name);
        return result;
    }
}
