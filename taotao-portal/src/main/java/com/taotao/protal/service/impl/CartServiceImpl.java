package com.taotao.protal.service.impl;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.protal.pojo.CartItem;
import com.taotao.protal.service.CartService;
import com.taotao.protal.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.taotao.protal.service.impl
 * @User 12428
 * @Date 2018/4/25 23:10
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemService itemService;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;
    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        // 1、接收商品id
        // 2、从cookie中获得购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        // 3、从商品列表中查询列表是否存在此商品
        boolean haveFlag = false;
        for ( CartItem cartItem : itemList ) {
            // 4、如果存在商品的数量加上参数中的商品数量
            if( cartItem.getId() == itemId ){
                cartItem.setNum(cartItem.getNum() + num);
                haveFlag = true;
                break;
            }
        }
        // 5、如果不存在在，调用rest服务，根据商品id获得商品数据
        if (!haveFlag){
            TbItem item = itemService.getItemById(itemId);
            CartItem cartItem = new CartItem(itemId, item.getTitle(), item.getPrice(), num, item.getImage());
            // 转换成CartItem
            if (StringUtils.isNotBlank(item.getImage())){
                String image = item.getImage();
                String[] strings = image.split(",");
                cartItem.setImage(strings[0]);
            }
            // 6、把商品数据添加到列表中
            itemList.add(cartItem);
        }
        // 7、把购物车商品列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
        // 8、返回结果
        return TaotaoResult.ok();
    }

    /**
     * 从cookie取购物车商品列表
     * @param request
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request){
        List<CartItem> list = null;
        try {
            // 从cookie取商品列表
            String json = CookieUtils.getCookieValue(request, "TT_CART", true);
            // 将json转换成java对象
            list = JsonUtils.jsonToList(json, CartItem.class);
            return list == null ? new ArrayList<CartItem>() : list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
