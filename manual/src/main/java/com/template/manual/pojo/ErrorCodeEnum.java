package com.template.manual.pojo;

public enum ErrorCodeEnum {
    SYSTEM_ERROR(1001,"系统错误"),
    INSERT_ERROR(1002,"插入失败"),
    REMOVE_ERROR(1003,"删除失败"),
    PATCH_REMOVE_ERROR(1004,"批量删除失败");
    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code=code;
    }

    public String getMsg(){
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
