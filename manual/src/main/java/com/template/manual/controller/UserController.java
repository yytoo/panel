package com.template.manual.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.manual.dto.user.RegisterIDTO;
import com.template.manual.pojo.ErrorCodeEnum;
import com.template.manual.util.BusinessException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.template.manual.service.IUserService;
import com.template.manual.pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Y
 * @since 2025-01-03
 */
@RestController
@Validated
@RequestMapping("/manual/user")
    public class UserController {

@Autowired
private IUserService userService;

@PostMapping("/register")
public Boolean register(@Valid @RequestBody RegisterIDTO registerIDTO){
    return userService.register(registerIDTO);
}

@GetMapping("/page")
public PageInfo<User> page(@RequestParam Integer currentPage,@RequestParam Integer pageSize,@RequestParam String param){
    QueryWrapper<User> queryWrapper=new QueryWrapper<>();
    if(!param.isBlank()){
        queryWrapper.like("param",param);
    }
    IPage<User> page = new Page<>(currentPage, pageSize);
    IPage<User> userPage=userService.page(page,queryWrapper);
    List<User> userList = userPage.getRecords();
    long total = userPage.getTotal();
     PageInfo<User> pageInfo = new PageInfo<>();
     pageInfo.setList(userList);
     return pageInfo;
}

@GetMapping("/findById/{id}")
public User findById(@PathVariable Integer id){
    return userService.getById(id);
}

@PostMapping("/saveOrUpdate")
public Boolean saveOrUpdate(@RequestBody User user){
    boolean code=userService.saveOrUpdate(user);
    if(code){
        return true;
    }else{
        throw new BusinessException(ErrorCodeEnum.INSERT_ERROR.getCode(), ErrorCodeEnum.INSERT_ERROR.getMsg());
    }
}



@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Long id){
    QueryWrapper<User> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("id",id);
    boolean code=userService.remove(queryWrapper);
    if(code){
        return true;
    }else{
        throw new BusinessException(ErrorCodeEnum.REMOVE_ERROR.getCode(), ErrorCodeEnum.REMOVE_ERROR.getMsg());
    }
}

@DeleteMapping("/ids/{ids}")
public Boolean deleteByIds(@PathVariable Long[] ids){
    List<Long> list = new ArrayList<>(Arrays.asList(ids));
    boolean code=userService.removeBatchByIds(list);
    if(code){
        return true;
    }else{
        throw new BusinessException(ErrorCodeEnum.PATCH_REMOVE_ERROR.getCode(), ErrorCodeEnum.PATCH_REMOVE_ERROR.getMsg());
    }
}

}


