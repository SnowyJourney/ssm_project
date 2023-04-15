package com.example.springboot_furn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_furn.entity.Furn;
import com.example.springboot_furn.mapper.FurnMapper;
import com.example.springboot_furn.service.FurnService;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【furn】的数据库操作Service实现
* @createDate 2023-04-14 22:33:36
*/
@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn>
    implements FurnService {

}




