package com.taotao.protal.pojo;

import com.taotao.pojo.TbItem;

import java.util.Date;

/**
 * 购物车商品pojo
 * @Package com.taotao.protal.pojo
 * @User 12428
 * @Date 2018/4/27 22:12
 */

public class CartItem {

    private Long id;

    private String title;

    private Long price;

    private Integer num;

    private String image;

    public CartItem() {
    }

    public CartItem(Long id, String title, Long price, Integer num, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.num = num;
        this.image = image;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
