package com.example.springboot_furn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_furn.entity.Furn;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Admin
* @description 针对表【furn】的数据库操作Mapper
* @createDate 2023-04-14 22:33:36
* @Entity com.example.springBoot_Furn.entity.Furn
*/
@Mapper
public interface FurnMapper extends BaseMapper<Furn> {

}




