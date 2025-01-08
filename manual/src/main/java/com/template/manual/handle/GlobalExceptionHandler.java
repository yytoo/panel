package com.template.manual.handle;

import com.template.manual.dto.user.ApiResponse;
import com.template.manual.pojo.ResultResponse;
import com.template.manual.util.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一处理400异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultResponse<Map<String, String>> handleException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResultResponse<>(HttpStatus.BAD_REQUEST.value(),"业务异常", errors) ;
    }


    /**
     * 捕获固定类型的异常, 此处是自定义的BusinessException, 用于springmvc返回时自身处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(500, "服务器内部错误");
        if (ex instanceof BusinessException) {
            BusinessException customEx = (BusinessException) ex;
            apiResponse.setCode(customEx.getCode());
            apiResponse.setMessage(customEx.getMessage());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
