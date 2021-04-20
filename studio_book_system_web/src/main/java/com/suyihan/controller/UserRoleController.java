package com.suyihan.controller;


import com.suyihan.entity.QueryUserRoleCondition;
import com.suyihan.entity.UserRole;
import com.suyihan.response.Result;
import com.suyihan.service.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    //根据用户所属角色id查找用户所属角色信息
    @RequestMapping("/selectByUserRoleId")
    public Result selectByuserRoleId(Integer userRoleId){
        return Result.error();
    }
    //根据用户所属角色名查找用户所属角色信息
    @RequestMapping("/selectByUserRoleName")
    public Result selectByUserRoleName(String userRoleName){
        return Result.error();
    }
    //条件查询
    @RequestMapping("/selectUserRoleByCondition")
    public Result selectUserRoleByCondition(QueryUserRoleCondition qurc){
        List<UserRole> userRoleList = userRoleService.selectByQueryUserRoleCondition(qurc);
        return Result.ok().data("result",userRoleList);
    }
    //添加用户所属角色数据
    @RequestMapping("/insertUserRole")
    public Result insertUserRole(UserRole userRole){
        return Result.error();
    }
    //添加用户所属角色数据
    @RequestMapping("/doAssignRoles")
    @RequiresPermissions("user:assign")
    public Result doAssignRoles(@RequestBody Map<String,Object> map){
        Long userId = (Long) map.get("userId");
        List<Long> roleIdsList = (List<Long>) map.get("roleIds");
        Long[] roleIds = roleIdsList.toArray(new Long[roleIdsList.size()]);
        Boolean flag = userRoleService.doAssignRoles(userId, roleIds);
        if (flag){
            return Result.ok();
        }
        return Result.error();
    }
    //根据用户所属角色ID删除用户所属角色信息
    @RequestMapping("/deleteUserRoleById")
    public Result deleteUserRoleById(Integer userRoleId){
        return Result.error();
    }
    //根据用户所属角色id查找用户所属角色信息
    @RequestMapping("/updateUserRoleById")
    public Result updateUserRoleById(UserRole userRole){
        return Result.error();
    }
}

