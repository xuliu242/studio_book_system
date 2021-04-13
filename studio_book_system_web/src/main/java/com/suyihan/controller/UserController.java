package com.suyihan.controller;


import com.suyihan.entity.User;
import com.suyihan.response.Result;
import com.suyihan.service.UserService;
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
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/selectAll")
    public Result selectAll(){
        List<User> userList = userService.list();
        return Result.ok().data("result",userList);
    }

}

