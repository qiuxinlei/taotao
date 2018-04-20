package com.taotao.protal.service;

import com.taotao.pojo.TaotaoResult;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Package com.taotao.protal.service
 * @User 12428
 * @Date 2018/4/17 23:33
 */

public interface StaticPageService {
    TaotaoResult genItemHtml(Long itemId) throws IOException, TemplateException;
}
