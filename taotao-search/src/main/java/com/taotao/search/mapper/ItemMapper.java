package com.taotao.search.mapper;

import com.taotao.search.pojo.SearchItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package com.taotao.search.mapper
 * @User 12428
 * @Date 2018/3/30 17:00
 */
@Repository
public interface ItemMapper  {
    List<SearchItem> getItemList();
}
