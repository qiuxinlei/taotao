package com.taotao.protal.controller;

import com.taotao.protal.pojo.SearchResult;
import com.taotao.protal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @Package com.taotao.protal.controller
 * @User 12428
 * @Date 2018/4/2 23:22
 */

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyword,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "60") Integer rows,
                               Model model){
        try {
            keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            keyword = "";
        }
        SearchResult search = searchService.search(keyword, page, rows);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",search.getPageCount());
        model.addAttribute("itemList",search.getItemList());
        model.addAttribute("page",search.getCurPage());
        //返回逻辑视图
        return "search";
    }
}
