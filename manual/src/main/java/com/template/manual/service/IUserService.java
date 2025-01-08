package com.template.manual.service;

import com.template.manual.dto.user.RegisterIDTO;
import com.template.manual.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Y
 * @since 2025-01-03
 */
public interface IUserService extends IService<User> {
    Boolean register(RegisterIDTO idto);
}
