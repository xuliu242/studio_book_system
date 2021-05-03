package com.suyihan.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suyihan.entity.QueryRoleCondition;
import com.suyihan.entity.Role;
import com.suyihan.entity.RoleTransfer;
import com.suyihan.response.Result;
import com.suyihan.service.RoleService;
import com.suyihan.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "角色管理模块")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    // 根据角色id查找角色信息
    @ApiOperation(value = "根据角色id查找角色信息")
    @RequestMapping(value = "/selectByRoleId",method = RequestMethod.GET)
//    @RequiresPermissions("role:select")
    public Result selectByRoleId(Long roleId) {
        Role role = roleService.selectByRoleId(roleId);
        if (role!=null){
            return Result.ok().data("result",role);
        }
        return Result.error();
    }
    // 前端需要的角色数据
    @ApiOperation(value = "前端需要的角色数据")
    @RequestMapping(value = "/selectAllRoles",method = RequestMethod.GET)
//    @RequiresPermissions("role:select")
    public Result selectAllRoles(Long userId) {
        List<Role> roleList = roleService.selectAll();
        List<RoleTransfer> roleTransferList=new ArrayList<>();
        for (Role role:roleList) {
            RoleTransfer roleTransfer=new RoleTransfer();
            roleTransfer.setKey(role.getSyhRoleId().intValue());
            roleTransfer.setLabel(role.getSyhRoleName());
            roleTransfer.setDisabled(role.getSyhRoleStatus()==0);
            roleTransferList.add(roleTransfer);
        }
        List<Long> roleIds = userRoleService.selectRoleIds(userId);
        return Result.ok().data("roles",roleTransferList).data("roleIds",roleIds);
    }

    // 根据角色名查找角色信息
    @ApiOperation(value = "根据角色名查找角色信息")
    @RequestMapping(value = "/selectByRoleName",method = RequestMethod.GET)
//    @RequiresPermissions("role:select")
    public Result selectByRoleName(String roleName) {
        List<Role> roles = roleService.selectRoleByRoleName(roleName);
        if (roles!=null){
            return Result.ok().data("result",roles);
        }
        return Result.error();
    }

    /**
     * 条件查询 角色
     * @param qrc
     * @return
     */
    @ApiOperation(value = "条件查询 角色")
    @RequestMapping(value = "/selectRoleByCondition",method = RequestMethod.POST)
//    @RequiresPermissions("role:select")
    public Result selectRoleByCondition(@RequestBody QueryRoleCondition qrc) {
//        int i=1/0;
        //       获取分页数据
        Integer pageNum = qrc.getPageNum()==null?1:qrc.getPageNum();
        Integer pageSize = qrc.getPageSize()==null?10:qrc.getPageSize();
        qrc.setRoleStopTime(new Date());

        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(roleService.selectRoleByCondition(qrc));

        return Result.ok().data("result",pageInfo.getList()).data("total",pageInfo.getTotal()).data("pages",pageInfo.getPages());
    }

    // 添加角色数据
    @ApiOperation(value = "添加角色数据")
    @RequestMapping(value = "/insertRole",method = RequestMethod.POST)
//    @RequiresPermissions("role:add")
    public Result insertRole(@RequestBody Role role) {
        role.setSyhRoleCreateTime(new Date());
        int i = roleService.insertRole(role);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    // 根据角色ID删除角色信息
    @ApiOperation(value = "根据角色ID删除角色信息")
    @RequestMapping(value = "/deleteRoleById",method = RequestMethod.GET)
//    @RequiresPermissions("role:delete")
    public Result deleteRoleById(Long roleId) {
        int i = roleService.deleteRoleById(roleId);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    // 根据角色ID更新角色信息
    @ApiOperation(value = "根据角色ID更新角色信息")
    @RequestMapping(value = "/updateRoleById",method = RequestMethod.POST)
//    @RequiresPermissions("role:update")
    public Result updateRoleById(@RequestBody Role role) {
        int i = roleService.updateRoleById(role);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 根据角色id更新role状态
     * @param map
     * @return
     */
    @ApiOperation(value = "根据角色id更新role状态")
    @RequestMapping(value = "/updateRoleStatusById",method = RequestMethod.POST)
//    @RequiresPermissions("role:update")
    public Result updateRoleStatusById(@RequestBody Map<String,Object> map) {
        String roleIdStr = (String) map.get("roleId");
        long roleId = Long.parseLong(roleIdStr);
        Integer roleHidden = (Integer) map.get("roleHidden");
        int i = roleService.updateRoleStatusById(roleId,roleHidden);
        if (i>0){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 多选删除角色
     * @param map
     * @return
     */
    @ApiOperation(value = "多选删除角色")
    @RequestMapping(value = "/deleteRoleByIdMultiple",method = RequestMethod.POST)
//    @RequiresPermissions("role:batch_delete")
    public Result deleteRoleByIdMultiple(@RequestBody Map<String,Object> map) {
        List<Object> list = (List<Object>) map.get("roleList");
        List<Role> roleList=new ArrayList<>();
        for (Object o:list) {
            String s = JSON.toJSONString(o);
            Role role = JSONObject.parseObject(s, Role.class);
            int i = roleService.deleteRoleById(role.getSyhRoleId());
            if (i <= 0) {
                return Result.error();
            }
        }
        return Result.ok();
    }

}

