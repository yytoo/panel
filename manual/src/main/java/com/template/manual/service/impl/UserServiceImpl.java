package com.template.manual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.template.manual.dto.user.RegisterIDTO;
import com.template.manual.pojo.User;
import com.template.manual.mapper.UserMapper;
import com.template.manual.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.manual.util.BusinessException;
import com.template.manual.util.Md5Util;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Y
 * @since 2025-01-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Transactional
    public Boolean register(RegisterIDTO idto){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", idto.getUserName());
        List<User> oldRecordList = this.list(queryWrapper);
        // 空则注册
        if(CollectionUtils.isEmpty(oldRecordList)){
            // 加密密码
            String pw = Md5Util.getMD5String(idto.getPassword());
            User user = new User();
            user.setUsername(idto.getUserName());
            user.setPassword(pw);
            LocalDateTime now = LocalDateTime.now();
            user.setUpdateTime(now);
            user.setCreateTime(now);
            //todo 需要填入操作人信息
            this.save(user);
        }else{
            throw new BusinessException("账号已注册");
        }
        return true;
    }

}
