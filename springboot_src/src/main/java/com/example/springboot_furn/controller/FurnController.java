package com.example.springboot_furn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot_furn.entity.Furn;
import com.example.springboot_furn.service.FurnService;
import com.example.springboot_furn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class FurnController {

    @Autowired
    FurnService furnService;

    @PostMapping("/save")
    public Result save(@Validated @RequestBody Furn furn, Errors errors){
//        封装错误信息
        HashMap<String, String> errMap = new HashMap<>();
//        获取错误信息
        List<FieldError> fieldErrors = errors.getFieldErrors();
//        封装到map中
        for (FieldError fieldError : fieldErrors) {
            errMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
//        判断是否通过验证
        if(errMap!=null)
            return Result.failure("提交数据有误",errMap);
//        按照正常流程
        furnService.save(furn);
        return Result.success();
    }

    @GetMapping(value = "/listAll")
    public Result listAll(){
        List<Furn> furns = furnService.list();
        return Result.success(furns);
    }

    @PutMapping(value = "/update")
    public Result update(@RequestBody Furn furn){
        boolean b = furnService.updateById(furn);
        if(b==false)
            return Result.failure("更新错误");
        return Result.success("更新成功");
    }

    @DeleteMapping("/del/{id}")
    public Result delOne(@PathVariable("id") Integer id){
        boolean b = furnService.removeById(id);
        if(b)
            return Result.success("删除成功");
        return Result.failure("删除失败");
    }

    @GetMapping("/findById/{id}")
    public Result findByID(@PathVariable("id")Integer id){
        Furn furn = furnService.getById(id);
        if(furn==null)
            return Result.failure("没有找到");
        return Result.success(furn);
    }

    @GetMapping(value = "/listPage")
    public Result listPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5")Integer pageSize){
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    @GetMapping("/conditionPage")
    public Result listPageWithCondition(@RequestParam(defaultValue = "1")Integer pageNum,
                                        @RequestParam(defaultValue = "6")Integer pageSize,
                                        @RequestParam(defaultValue = "")String search){
        QueryWrapper<Furn> queryMapper = Wrappers.query();

        if(StringUtils.hasText(search)){
            queryMapper.like("name",search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize),queryMapper);
        return Result.success("查找成功",page);
    }

}
