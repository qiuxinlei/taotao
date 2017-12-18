package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-*.xml"})
public class TestPageHelper {

    @Autowired
    private TbItemMapper tbItemMapper;
    //注解加载配置问价
    @Test
    public void testPageHelper(){
        //设置分页
        PageHelper.startPage(1,30);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);
        //取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("total:"+total);
        int pages = pageInfo.getPages();
        System.out.println("pages:"+pages);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize:"+pageSize);
    }

    //手动加载配置文件
    @Test
    public void testPageHelper1(){
        //获得mapper代理对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
        //设置分页
        PageHelper.startPage(1,30);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("total:"+total);
        int pages = pageInfo.getPages();
        System.out.println("pages:"+pages);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize:"+pageSize);
    }
}
