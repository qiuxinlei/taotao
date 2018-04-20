package com.taotao.protal.service.impl;

import com.taotao.pojo.*;
import com.taotao.protal.pojo.ProtalItem;
import com.taotao.protal.service.ItemService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/4/16 19:32
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_BASE_URL}")
    private String REST_ITEM_BASE_URL;
    @Value("${REST_ITEM_DESC_URL}")
    private String REST_ITEM_DESC_URL;
    @Value("${REST_ITEM_PARAM_URL}")
    private String REST_ITEM_PARAM_URL;

    @Override
    public TbItem getItemById(Long itemId) {
        String url = REST_BASE_URL + REST_ITEM_BASE_URL + itemId;
        //根据商品id查询商品基本信息
        String json = HttpClientUtil.get(url);
        //转换成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json, ProtalItem.class);
        //去商品对象
        TbItem item = (TbItem) result.getData();
        return item;
    }

    @Override
    public String getItemDescById(Long itemId) {
        //根据商品id调用taotao-rest的服务获得数据
        //http://localhost:8081/rest/item/desc/144766336139977
        String url = REST_BASE_URL+REST_ITEM_DESC_URL+itemId;
        String json = HttpClientUtil.get(url);
        //转换成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json,TbItemDesc.class);
        TbItemDesc itemDesc = (TbItemDesc) result.getData();
        String desc = itemDesc.getItemDesc();
        return desc;
    }

    @Override
    public String getItemParamById(Long itemId) {
        //根据商品id获得对应的规格参数
        String url = REST_BASE_URL + REST_ITEM_PARAM_URL + itemId;
        String json = HttpClientUtil.get(url);
        TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        TbItemParamItem itemParam = (TbItemParamItem) result.getData();
        String paramData = itemParam.getParamData();
        //将规格参数的json转为java对象
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
}
