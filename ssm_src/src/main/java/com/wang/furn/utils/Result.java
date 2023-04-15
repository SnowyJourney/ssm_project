package com.wang.furn.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private  int code;
    private  String massage;
    private  Map<String,Object> data = new HashMap<>();

    public Result success(){
        setMassage("ok");
        setCode(200);
        return this;
    }

    public Result error(){
        setMassage("error");
        setCode(500);
        return this;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setMassage(String massage) {
        this.massage = massage;
        return this;
    }

    public Result setData(String key, Object value) {
        data.put(key,value);
        return this;
    }
}
