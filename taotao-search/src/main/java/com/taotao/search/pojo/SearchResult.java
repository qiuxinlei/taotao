package com.taotao.search.pojo;

import java.util.List;

/**
 * @Package com.taotao.search.pojo
 * @User 12428
 * @Date 2018/3/30 19:41
 */

public class SearchResult {
    private List<SearchItem> itemList;
    private Long recoreCount;
    private Long pageCount;
    private Long curPage;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getRecoreCount() {
        return recoreCount;
    }

    public void setRecoreCount(Long recoreCount) {
        this.recoreCount = recoreCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }
}
