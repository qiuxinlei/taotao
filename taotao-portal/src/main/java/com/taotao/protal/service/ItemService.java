package com.taotao.protal.service;

import com.taotao.pojo.TbItem;

/**
 * @Package com.taotao.protal.service
 * @User 12428
 * @Date 2018/4/16 19:31
 */

public interface ItemService {
    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
