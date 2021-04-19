package com.suyihan.controller;

import com.suyihan.entity.User;
import com.suyihan.handle.BusinessException;
import com.suyihan.response.Result;
import com.suyihan.response.ResultCode;
import com.suyihan.service.UserService;
import com.suyihan.util.MD5Utils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "登录模块")
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result doLogin(@RequestBody User user, HttpServletResponse response){
        User loginUser = userService.selectUserByLoginName(user.getSyhUserNumber());
        if (loginUser==null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND_EXCEPTION.getCode(),
                    ResultCode.USER_NOT_FOUND_EXCEPTION.getMessage());
        }
        String md5Encryption = MD5Utils.md5Encryption(user.getSyhPassword(), loginUser.getSyhUserName());
        String substring = md5Encryption.substring(8, 24);
        if(!loginUser.getSyhPassword().equals(substring)){
            return Result.error().message("密码错误");
        }
        if (loginUser.getSyhStatus()==0){
            return Result.error().message("账户已锁定");
        }
        loginUser.setSyhPassword(null);
        return Result.ok().data("userInfo",loginUser);

    }

}
