package com.wang;

import com.wang.furn.controller.FurnController;
import com.wang.furn.entity.Furn;
import com.wang.furn.service.FurnService;
import com.wang.furn.utils.Result;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class testAll {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        FurnService furnService = ioc.getBean(FurnService.class);
        List<Furn> furnList = furnService.selectLikeName("洗");
    }

    @Test
    public void generator() throws Exception{
        ArrayList<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("mybatis-generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
