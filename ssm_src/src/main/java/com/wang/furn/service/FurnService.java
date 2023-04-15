package com.wang.furn.service;

import com.wang.furn.entity.Furn;

import java.util.List;

public interface FurnService {

    public int saveOne(Furn furn);

    public List<Furn> findAll();

    public int updateOne(Furn furn);

    public int delOne(Integer id);

    public Furn selectByOne(Integer id);

    public List<Furn> selectLikeName(String name);
}
