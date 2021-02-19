package com.suyihan.controller;


import com.suyihan.entity.User;
import com.suyihan.handle.BusinessException;
import com.suyihan.response.Result;
import com.suyihan.response.ResultCode;
import com.suyihan.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUsers")
    @ApiOperation(value = "查询所有用户信息")
    public Result findUsers(){
        int i=1/0;
        List<User> list = userService.list();
        return Result.ok().data("userList",list);
    }
    @RequestMapping("/findUserById")
    @ApiOperation(value = "根据id查询用户")
    public Result findUserById(){

        User user = userService.getById("ddsadwdas");
        if (user!=null){
            return Result.ok().data("user",user);
        }else {
            throw new BusinessException(ResultCode.USER_NOT_FOUND_EXCEPTION.getCode(),
                    ResultCode.USER_NOT_FOUND_EXCEPTION.getMessage());
        }

    }

}

