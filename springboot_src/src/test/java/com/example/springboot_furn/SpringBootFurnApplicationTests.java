package com.example.springboot_furn;

import com.example.springboot_furn.entity.Furn;
import com.example.springboot_furn.mapper.FurnMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFurnApplicationTests {

    @Autowired
    FurnMapper furnMapper;

    @Test
    void contextLoads() {
        Furn furn = furnMapper.selectById(1);
        System.out.println(furnMapper.getClass());
        System.out.println(furn);
    }

}
