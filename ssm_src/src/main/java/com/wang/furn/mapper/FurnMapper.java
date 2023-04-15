package com.wang.furn.mapper;

import com.wang.furn.entity.Furn;
import com.wang.furn.entity.FurnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FurnMapper {
    long countByExample(FurnExample example);

    int deleteByExample(FurnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Furn row);

    int insertSelective(Furn row);

    List<Furn> selectByExample(FurnExample example);

    Furn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Furn row, @Param("example") FurnExample example);

    int updateByExample(@Param("row") Furn row, @Param("example") FurnExample example);

    int updateByPrimaryKeySelective(Furn row);

    int updateByPrimaryKey(Furn row);
}