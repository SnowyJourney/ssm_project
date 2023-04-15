package com.wang.furn.service.serviceImpl;

import com.wang.furn.entity.Furn;
import com.wang.furn.entity.FurnExample;
import com.wang.furn.mapper.FurnMapper;
import com.wang.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FurnServiceImpl implements FurnService {

    @Resource
    private FurnMapper furnMapper;

    @Override
    public int saveOne(Furn furn) {
        return furnMapper.insertSelective(furn);
    }

    @Override
    public List<Furn> findAll() {
        return furnMapper.selectByExample(null);
    }

    @Override
    public int updateOne(Furn furn) {
       return furnMapper.updateByPrimaryKeySelective(furn);
    }

    @Override
    public int delOne(Integer id) {
       return furnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Furn selectByOne(Integer id) {
        return furnMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Furn> selectLikeName(String name) {
        FurnExample furnExample = new FurnExample();
        FurnExample.Criteria criteria = furnExample.createCriteria();
        if(StringUtils.hasText(name)){
            criteria.andNameLike('%'+name+'%');
        }
        List<Furn> furnList = furnMapper.selectByExample(furnExample);
        return furnList;
    }
}
