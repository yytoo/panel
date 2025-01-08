package com.template.manual.dto.user;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterIDTO {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 100, message = "用户名长度必须在2到100位之间")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 100, message = "密码长度必须在8到100位之间")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "密码必须包含至少一个大小写字母 一个数字 和一个特殊字符")
    private String password;
}
