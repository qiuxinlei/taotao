package com.taotao.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {

        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        TbItem item = null;
        if(tbItems!=null && tbItems.size()>0){
            item = tbItems.get(0);
        }
        return item;
    }
}
