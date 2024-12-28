package com.template.manual.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.manual.pojo.ErrorCodeEnum;
import com.template.manual.pojo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.template.manual.service.IUserService;
import com.template.manual.pojo.User;

import java.util.ArrayList;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Y
 * @since 2024-12-28
 */
@RestController
@RequestMapping("/manual/user")
public class UserController {

@Autowired
private IUserService userService;

@GetMapping("/page")
public ResultResponse page(@RequestParam Integer currentPage,@RequestParam Integer pageSize,@RequestParam String param){
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
     return ResultResponse.success(pageInfo);
}

@GetMapping("/findById/{id}")
public ResultResponse findById(@PathVariable("id") Integer id){
    User record = userService.getById(id);
    return ResultResponse.success(record);
}

@PostMapping("/saveOrUpdate")
public ResultResponse saveOrUpdate(@RequestBody User user){
    Boolean code=userService.saveOrUpdate(user);
    if(code==true){
        return ResultResponse.success();
    }else{
        return ResultResponse.error(ErrorCodeEnum.INSERT_ERROR.getCode(), ErrorCodeEnum.INSERT_ERROR.getMsg());
    }
}



@DeleteMapping("/{id}")
public ResultResponse delete(@PathVariable Integer id){
    QueryWrapper<User> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("id",id);
    Boolean code=userService.remove(queryWrapper);
    if(code==true){
        return ResultResponse.success();
    }else{
        return ResultResponse.error(ErrorCodeEnum.REMOVE_ERROR.getCode(), ErrorCodeEnum.REMOVE_ERROR.getMsg());
    }
}

@DeleteMapping("/ids/{ids}")
public ResultResponse deleteByIds(@PathVariable Integer[] ids){
    List<Integer> list=new ArrayList<>();
    for(Integer id:ids){
        list.add(id);
    }
    Boolean code=userService.removeBatchByIds(list);
    if(code==true){
        return ResultResponse.success();
    }else{
        return ResultResponse.error(ErrorCodeEnum.PATCH_REMOVE_ERROR.getCode(), ErrorCodeEnum.PATCH_REMOVE_ERROR.getMsg());
    }
}

}


