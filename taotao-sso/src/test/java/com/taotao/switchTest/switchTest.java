package com.taotao.switchTest;

import org.junit.Test;

/**
 * @Package com.taotao.switchTest
 * @User 12428
 * @Date 2018/4/20 20:03
 */

public class switchTest {

    @Test
    public void sTest(){
        int type = 1;
        //1、2、3分别代表username、phone、email
        switch (type){
            case 1:
                System.out.println(type);break;
            case 2:
                System.out.println(type);break;
            case 3:
                System.out.println(type);break;
            default:
                System.out.println(type);
        }
    }
}
