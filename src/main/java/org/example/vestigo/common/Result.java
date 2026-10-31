package org.example.vestigo.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result (){ }   //防止乱输数据
    // 无参的成功返回 （只返回状态码）
    public static <T> Result<T> success(){  // 一个公有类和一个带有接收任何数据类型的静态方法
        Result<T> result = new Result<>();
        result.code = 200;  // 状态码
        result.message = "操作成功";
        return result;

    }
    // 有参的成功返回
    public static <T>Result<T> success(T data){
        Result<T> result = new Result<>(); // 造盒子
        result.code = 200;
        result.data = data;
        return result;
    }

    //报错返回
    public static <T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.code = 500;
        result.message = message;  // 信息
        return result;
    }
}
