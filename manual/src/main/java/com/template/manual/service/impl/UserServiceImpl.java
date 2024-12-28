package com.template.manual.service.impl;

import com.template.manual.pojo.User;
import com.template.manual.mapper.UserMapper;
import com.template.manual.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Y
 * @since 2024-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
