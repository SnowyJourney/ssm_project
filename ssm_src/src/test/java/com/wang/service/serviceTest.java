package com.wang.service;

import com.wang.furn.entity.Furn;
import com.wang.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.math.BigDecimal;

public class serviceTest {

    private  ApplicationContext ioc;

    private FurnService furnService;

    @Before
    public void before(){
          ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        furnService = ioc.getBean(FurnService.class);
        System.out.println(furnService.getClass());
    }

    @Test
    public void test(){
        furnService.saveOne(new Furn("洗衣机", "海尔", new BigDecimal(700), 100, "d:/images"));
    }
}
