package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface ItemService {

    public TbItem getItemById(Long itemId);
    public EasyUIDataGridResult getItemList(int page,int rows);
    public TaotaoResult createItem(TbItem item,String desc,String itemParam);
    public EasyUIDataGridResult getParamList(int page,int rows);
    public String getItemParamHtml(Long itemId);

}
