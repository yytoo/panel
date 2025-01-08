package com.template.manual.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> implements Serializable {
    private int code;
    private String mgs;
    private T data;

    public static ResultResponse success(){
        return new ResultResponse(1,"success","");
    }

    // 静态工厂方法，这里需要显式声明 <E>, 而非函数, 提供了一种创建对象的替代方式
    //静态工厂方法可以返回实现接口或继承自某个类的任何子类型，而不仅仅是声明类型的实例。
    public static <T> ResultResponse<T> success(T data){
        return new ResultResponse(1,"success",data);
    }
    public static ResultResponse error(int code,String msg){
        return new ResultResponse(code,msg,"");
    }
}
