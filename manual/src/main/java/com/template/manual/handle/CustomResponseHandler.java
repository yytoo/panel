package com.template.manual.handle;
import com.template.manual.dto.user.ApiResponse;
import com.template.manual.util.ResponseUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * springMVC返回的结果外层再次封装(非异常)
 */
@Aspect
@Component
public class CustomResponseHandler {

    @AfterReturning(pointcut = "@annotation(AutoWrapResponse)", returning = "result")
    public Object wrapResponse(Object result) {
        // 如果是已经封装好的返回结果, 则不再次处理(业务自己做异常的包装)
        if (result instanceof ApiResponse) {
            return result;
        }
        return ResponseUtils.success(result);
    }
}