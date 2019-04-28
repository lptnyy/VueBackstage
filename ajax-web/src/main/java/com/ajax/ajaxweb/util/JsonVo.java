package com.ajax.ajaxweb.util;

import com.alibaba.fastjson.JSON;

public class JsonVo<T> {
    T obj;
    boolean result;
    String msg;
    Integer dataCount;
    String callback;

    public String toString(){
        String jsonStr = JSON.toJSONString(this);
        if (callback != null && callback.trim().equals("true")){
            jsonStr = "callback("+jsonStr+")";
        }
        return jsonStr;
    }

    public T getObj() {
        return obj;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }
}
