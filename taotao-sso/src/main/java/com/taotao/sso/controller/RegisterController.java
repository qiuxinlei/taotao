package com.taotao.sso.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;
import com.taotao.utils.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Package com.taotao.sso.controller
 * @User 12428
 * @Date 2018/4/20 20:08
 */
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,@PathVariable Integer type, String callback){
        try {
            TaotaoResult result = registerService.checkData(param, type);
            if(StringUtils.isNotBlank(callback)){
                //请求为jsonjp调用，需要支持
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public TaotaoResult register(TbUser user){
//        System.out.println(user.getUsername());
        try {
            TaotaoResult result = registerService.register(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }
}
