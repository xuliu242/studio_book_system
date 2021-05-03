package com.suyihan.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suyihan.entity.*;
import com.suyihan.response.Result;
import com.suyihan.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
@Api(tags = "用户管理模块")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //    根据用户id查找用户信息
    @RequestMapping("/selectByUserId")
    @ResponseBody
    public Result selectByUserId(Long userId) {
        User user = userService.selectByUserId(userId);
        if (user!=null){
            return Result.ok().data("result",user);
        }
        return Result.error().message("无此用户");
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(){
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(activeUser.getUser().getSyhUserName());
        userInfo.setSyhUserNumber(activeUser.getUser().getSyhUserNumber());
        userInfo.setUrl(activeUser.getUrls());
        userInfo.setPerms(activeUser.getPermissions());
        List<Role> roles = activeUser.getRoles();
        List<String> roleList=new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            String roleName = roles.get(i).getSyhRoleName();
            roleList.add(roleName);
        }
        userInfo.setRoles(roleList);
        return Result.ok().data("userInfo",userInfo);
    }

    //    根据用户名查找用户信息
//    @RequestMapping("/selectByLoginName")
//    @ResponseBody
////    @RequiresPermissions("user:select")
//    public Result selectByLoginName(String userLoginName) {
//        User user = userService.selectUserByLoginName(userLoginName);
//        if (user!=null){
//            return Result.ok().data("result",user);
//        }
//        return Result.error().message("无此用户");
//
//    }

    // 查询所有用户信息
    @RequestMapping("/selectAll")
    @ResponseBody
//    @RequiresPermissions("user:select")
    public Result selectAll() {
        PageHelper.startPage(1,10);
//        List<User> userList = userService.selectAll();
//        for (int i = 0; i < 30; i++) {
//            User user=new User();
//            user.setSyhUserNumber(1740110500+i);
//            user.setSyhPassword("123456");
//            user.setSyhRegisterTime(new Date());
//            user.setSyhSex(i%2==0?1:0);
//            user.setSyhUserName("user"+(i+1));
//            user.setSyhPhone("135123400"+(i>10?"0"+i:i));
//            user.setSyhStatus(i%3==0?1:0);
//            user.setSyhEmail("1351234000"+i+"@qq.com");
//            int insertUser = userService.insertUser(user);
//            if (insertUser<=0)
//                return Result.ok();
//        }
        PageInfo pageInfo=new PageInfo(userService.selectAll());
        return Result.ok().data("userList", pageInfo.getList()).data("total",pageInfo.getTotal());
    }

    //    条件查询
    @RequestMapping("/selectUserByCondition")
    @ResponseBody
//    @RequiresPermissions("user:select")
    public Result selectUserByCondition(@RequestBody QueryUserCondition quc) {
//       获取分页数据
        Integer pageNum = quc.getPageNum()==null?1:quc.getPageNum();
        Integer pageSize = quc.getPageSize()==null?10:quc.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
//        获取条件
        List<User> result=new ArrayList<>();
        PageInfo pageInfo=new PageInfo(userService.selectUserByCondition(quc));


        return Result.ok().data("result",pageInfo.getList()).data("total",pageInfo.getTotal()).data("pages",pageInfo.getPages());
    }

    //    添加用户数据
    @RequestMapping("/insertUser")
//    @RequiresPermissions("user:add")
    @ResponseBody
    public Result insertUser(@RequestBody User user) {
        user.setSyhRegisterTime(new Date());
        int i = userService.insertUser(user);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID删除用户信息
    @RequestMapping("/deleteUserById")
    @ResponseBody
//    @RequiresPermissions({"user:delete"})
    public Result deleteUserById(Long userId) {
        int i = userService.deleteUserById(userId);
        if (i > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID更新用户信息
    @RequestMapping("/updateUserById")
//    @RequiresPermissions("user:update")
    @ResponseBody
    public Result updateUserById(@RequestBody User user) {
//        user.s(new Date());
        int i = userService.updateUserById(user);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    //    根据用户ID更新用户状态
    @RequestMapping("/updateUserStatusById")
//    @RequiresPermissions("user:update")
    @ResponseBody
    public Result updateUserStatusById(@RequestBody Map<String,Object> map) {
        String userIdStr = (String) map.get("userId");
        long userId = Long.parseLong(userIdStr);
        Integer userStatus = (Integer) map.get("userStatus");
        int i = userService.updateUserStatusById(userId, userStatus);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
    @RequestMapping("/deleteUserByIdMultiple")
    @ResponseBody
//    @RequiresPermissions("user:batch_delete")
    public Result deleteUserByIdMultiple(@RequestBody Map<String,Object> map) {
        List<Object> list = (List<Object>) map.get("userList");
        for (Object o:list) {
            String s = JSON.toJSONString(o);
            User user = JSONObject.parseObject(s, User.class);
            int i = userService.deleteUserById(user.getSyhUserId());
            if (i <= 0) {
                return Result.error();
            }
        }
        return Result.ok();
    }
    //    根据用户ID更新用户信息/移动端
    @RequestMapping("/mUpdateUserById")
    @ResponseBody
    public Result mUpdateUserById(@RequestBody User user) {
        User userServiceById = userService.getById(user.getSyhUserId());
        user.setSyhUserNumber(userServiceById.getSyhUserNumber());
        user.setSyhPassword(userServiceById.getSyhPassword());
        user.setSyhRegisterTime(userServiceById.getSyhRegisterTime());
        if (user.getSyhSex()==2){
            user.setSyhSex(0);
        }
        int i = userService.updateUserById(user);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }
}

