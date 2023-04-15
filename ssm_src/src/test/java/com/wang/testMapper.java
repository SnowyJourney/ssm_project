package com.wang;

import com.wang.furn.entity.Furn;
import com.wang.furn.mapper.FurnMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;


public class testMapper {

    @Test
    public void insertSelective(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println(furnMapper);
        int i = furnMapper.insertSelective(new Furn("杯子", "西安", new BigDecimal(10), 200, "d:/images"));
        System.out.println(i);
    }

}
