package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.taotao.rest.service.impl
 * @User 12428
 * @Date 2018/3/20 20:00
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public ItemCatResult getItemCatList() {
        List catList = getItemCatList(0L);
        ItemCatResult result = new ItemCatResult();
        result.setData(catList);
        return result;
    }
    public List getItemCatList(Long parentId){
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList<>();
        int cnt = 0;
        for (TbItemCat tbItemCat : list) {
            if(cnt>13) break;
            if(tbItemCat.getIsParent()){
                CatNode node = new CatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");
                if(tbItemCat.getParentId()==0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                    cnt ++;
                }else{
                        node.setName(tbItemCat.getName());
                }
                node.setItems(getItemCatList(tbItemCat.getId()));
                resultList.add(node);
            }else{
                String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;
    }
}
