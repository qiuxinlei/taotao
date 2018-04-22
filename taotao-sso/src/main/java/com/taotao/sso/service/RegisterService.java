package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @Package com.taotao.sso.service
 * @User 12428
 * @Date 2018/4/20 19:47
 */

public interface RegisterService {

    TaotaoResult checkData(String param,int type);
    TaotaoResult register(TbUser user);
}
