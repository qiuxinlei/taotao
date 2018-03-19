package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public TbItem getItemById(Long itemId) {

        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        TbItem item = null;
        if(tbItems!=null && tbItems.size()>0){
            item = tbItems.get(0);
        }
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //处理分页信息
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }


    @Override
    public EasyUIDataGridResult getParamList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemParamItemExample example = new TbItemParamItemExample();
        List<TbItemParamItem> list = itemParamItemMapper.selectByExample(example);
        PageInfo<TbItemParamItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        //根据商品id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list == null || list.isEmpty()){
            return "";
        }
        //去规格参数
        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        //转换java对象
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
        //遍历list
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding =\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("<tbody>\n");
        for (Map map : mapList) {
            sb.append("<tr>\n");
            sb.append("<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append(("</tr>\n"));
            List<Map> mapList2 = (List<Map>) map.get("params");
            sb.append("<tr>\n");
            for ( Map map2 : mapList2) {
                sb.append("<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("<td>"+map2.get("v")+"</td>\n");
                sb.append("</tr>\n");
            }
        }
        sb.append("</tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc,String itemParam) {
        //生成商品id
        Long itemId = IDUtils.genItemId();
        //补全TbItem属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品表
        itemMapper.insert(item);
        //商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //添加商品规格参数处理
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        itemParamItemMapper.insert(itemParamItem);
        //插入商品描述数据
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
}
