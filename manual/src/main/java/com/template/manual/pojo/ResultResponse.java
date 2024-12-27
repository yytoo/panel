package com.template.manual.pojo;

public class ResultResponse {
    private int code;
    private String msg;
    private Object data;

    public ResultResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultResponse success(){
        return new ResultResponse(200,"success",null);
    }
    public static ResultResponse success(Object data){
        return new ResultResponse(200,"success",data);
    }
    public static ResultResponse error(int code,String msg){
        return new ResultResponse(code,msg,null);
    }
}
