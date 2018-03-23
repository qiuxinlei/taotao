package com.taotao.service;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;

import java.util.List;

/**
 * @Package com.taotao.service
 * @User 12428
 * @Date 2018/3/21 19:37
 */

public interface ContentCatgoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);
    TaotaoResult insertCategory(Long parentId, String name);
}
