package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface ItemService {

    public TbItem getItemById(Long itemId);
    public EasyUIDataGridResult getItemList(int page,int rows);
}
