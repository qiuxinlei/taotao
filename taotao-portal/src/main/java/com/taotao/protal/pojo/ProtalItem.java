package com.taotao.protal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @Package com.taotao.protal.pojo
 * @User 12428
 * @Date 2018/4/17 15:29
 */

public class ProtalItem extends TbItem {

    public String[] getImages() {
        String images = this.getImage();
        if (images != null && !"".equals(images.trim())){
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;
    }
}
