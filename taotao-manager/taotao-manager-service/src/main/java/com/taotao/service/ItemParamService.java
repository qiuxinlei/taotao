package com.taotao.service;

import com.taotao.pojo.TaotaoResult;
import org.springframework.stereotype.Service;

public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);
}
