package com.taotao.protal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.protal.pojo.ProtalItem;
import com.taotao.protal.service.ItemService;
import com.taotao.protal.service.StaticPageService;
import com.taotao.utils.HttpClientUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成商品静态页面
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/4/17 23:33
 */

@Service
public class StaticPageServiceImpl implements StaticPageService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${STATIC_PAGE_PATH}")
    private String STATIC_PAGE_PATH;

    @Override
    public TaotaoResult genItemHtml(Long itemId) throws IOException, TemplateException {
        //获得商品基本信息
        TbItem item = itemService.getItemById(itemId);
        //获得商品描述
        String itemDesc = itemService.getItemDescById(itemId);
        //获得商品规格参数
        String itemParam = itemService.getItemParamById(itemId);
        //生成静态页面
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");
        //创建数据集
        Map root = new HashMap<>();
        //向数据集中添加属性
        root.put("item",item);
        root.put("itemDesc",itemDesc);
        root.put("itemParam",itemParam);
        //创建一个Writer对象
        Writer out = new FileWriter(new File(STATIC_PAGE_PATH+itemId+".html"));
        //生成静态文件
        template.process(root,out);

        out.flush();
        out.close();
        return TaotaoResult.ok();
    }
}
