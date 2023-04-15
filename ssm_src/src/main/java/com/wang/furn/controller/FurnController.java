package com.wang.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.furn.entity.Furn;
import com.wang.furn.service.FurnService;
import com.wang.furn.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class FurnController {

    @Resource
    private FurnService furnService;

    @PostMapping(value = "/save")
    public Result addFurn(@Validated @RequestBody Furn furn, Errors errors){
//        封装错误信息
        Map<String, Object> errMap = new HashMap<>();
//        获取所有字段校验的错误信息
        List<FieldError> fieldErrors = errors.getFieldErrors();
//        如果校验数据有误
        if(!fieldErrors.isEmpty()){
//            封装错误信息到map中
            for (FieldError fieldError : fieldErrors) {
                errMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new Result().error().setData("error",errMap);
        }
//        如果验证数据通过
        furnService.saveOne(furn);
        Result result = new Result();
        result.success();
        return result;
    }

    @GetMapping(value = "/listFurn")
    public Result listFurn(){
        List<Furn> furn = furnService.findAll();
        Result result = new Result();
        result.setCode(200).setMassage("ok");
        result.setData("furnList",furn);
        return result;
    }

    @PutMapping(value = "/modifyOne")
    public Result modifyOne(@RequestBody Furn furn){
        furnService.updateOne(furn);
        Result result = new Result();
        result.success();
        return result;
    }

    @DeleteMapping(value = "/del/{id}")
    public Result del(@PathVariable Integer id){
        int i = furnService.delOne(id);
        Result result = new Result();
        if(i==0)
            result.error();
        else
            result.success();
        return result;
    }

    @GetMapping(value = "/findById")
    public Result findById(Integer id){
        Furn furn = furnService.selectByOne(id);
        if(furn==null)
            return new Result().error();
        Result result = new Result();
        result.success();
        result.setData("furn",furn);
        return result;
    }

    @GetMapping(value = "/findPage")
    public Result findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "3") Integer pageSize){
//        设置查询起始页和地址
        PageHelper.startPage(pageNum,pageSize);
        List<Furn> furnList = furnService.findAll();
//        封装分页信息
        PageInfo pageInfo = new PageInfo(furnList, pageSize);
        return new Result().success().setData("furnList",pageInfo);
    }

//    分页查询-条件
    @GetMapping(value = "/search")
    public Result findByName(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "3") Integer pageSize,String name){
//        设置查询起始页和地址
        PageHelper.startPage(pageNum,pageSize);
        List<Furn> furnList = furnService.selectLikeName(name);
//        封装分页信息
        PageInfo pageInfo = new PageInfo(furnList, pageSize);
        return new Result().success().setData("furnList",pageInfo);
    }
}
