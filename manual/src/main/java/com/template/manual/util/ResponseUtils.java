package com.template.manual.util;

import com.template.manual.dto.user.ApiResponse;
import com.template.manual.enums.ResponseCodeEnum;

public class ResponseUtils {

    private ResponseUtils() {}

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage());
    }

    public static ApiResponse<Void> error(ResponseCodeEnum codeEnum) {
        return new ApiResponse<>(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <T> ApiResponse<T> error(ResponseCodeEnum codeEnum, T data) {
        return new ApiResponse<>(codeEnum.getCode(), codeEnum.getMessage(), data);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }
}