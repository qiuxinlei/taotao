package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * @Package com.taotao.rest.service
 * @User 12428
 * @Date 2018/4/9 21:42
 */

public interface ItemService {

    TbItem getItemById(Long itemId);
    TbItemDesc getItemDescById(Long itemId);
    TbItemParamItem getItemParamById(Long itemId);
}
