package com.taotao.rest.controller;

import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * @Package com.taotao.rest.controller
 * @User 12428
 * @Date 2018/3/20 20:19
 */

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

//    @RequestMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        ItemCatResult result = itemCatService.getItemCatList();
//        String json = JsonUtils.objectToJson(result);
//
//        if(StringUtils.isBlank(callback)){
//            return json;
//        }else{
//            return callback +"("+json+");";
//        }
//    }
    @RequestMapping(value="/list")
    @ResponseBody
    public Object getItemCatList(String callback){
        ItemCatResult result = itemCatService.getItemCatList();
        if(StringUtils.isBlank(callback)){
            return result;
        }else{
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }
}
