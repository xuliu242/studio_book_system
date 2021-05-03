package com.suyihan.controller;


import com.alibaba.fastjson.JSONArray;
import com.suyihan.entity.Menu;
import com.suyihan.response.Result;
import com.suyihan.service.MenuService;
import com.suyihan.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
@Api(tags = "角色-菜单管理模块")
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    /**
     * 根据登录账号id查询菜单
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据登录账号id查询菜单")
    @RequestMapping(value = "/selectMenuByUserId", method = RequestMethod.GET)
    public Result selectMenuByUserId(Long userId) {
        List<Menu> menuList = roleMenuService.selectMenuByUserId(userId);
        System.out.println(menuList.toString());
        return Result.ok().data("result", menuList);
    }

    /**
     * 根据角色权限id查找角色权限信息
     *
     * @param roleMenuId
     * @return
     */
    @ApiOperation(value = "根据角色权限id查找角色权限信息")
    @RequestMapping(value = "/selectByRoleMenuId", method = RequestMethod.GET)
    public Result selectByRoleMenuId(Integer roleMenuId) {
        return Result.error();
    }

    /**
     * 添加角色权限数据
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "添加角色权限数据")
    @RequestMapping(value = "/insertRoleMenu", method = RequestMethod.POST)
//    @RequiresPermissions("role:assignMenu")
    public Result insertRoleMenu(@RequestBody Map<String, Object> map) {
        String roleIdInt = (String) map.get("roleId");
        long roleId = Long.parseLong(roleIdInt);
        List<String> menuIdsStr = (List<String>) map.get("menuIds");
        List<Long> menuIds = menuIdsStr.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
//        List<Long> menuIds = JSONArray.parseArray(menuIdsInt.toString(),Long.class);
        Boolean flag = roleMenuService.insertRoleMenu(roleId, menuIds);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 根据角色权限ID删除角色权限信息
     *
     * @param roleMenuId
     * @return
     */
    @ApiOperation(value = "根据角色权限ID删除角色权限信息")
    @RequestMapping(value = "/deleteRoleMenuById", method = RequestMethod.GET)
    public Result deleteRoleMenuById(Long roleMenuId) {
        int i = roleMenuService.deleteRoleMenuById(roleMenuId);
        return Result.error();
    }

    /**
     * 根据角色id查找角色权限信息
     *
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据角色id查找角色权限信息")
    @RequestMapping(value = "/selectByRoleId", method = RequestMethod.POST)
    public Result selectByRoleId(@RequestBody Long roleId) {
        List<Menu> menuByRoleIdList = roleMenuService.selectByRoleId(roleId);
        List<String> menuIds = new ArrayList<>();
        for (Menu menu : menuByRoleIdList) {
            menuIds.add(menu.getSyhMenuId().toString());
        }
        List<Menu> menuList = menuService.selectMenuByCondition(null);
        return Result.ok().data("menuList", menuList).data("menuIds", menuIds);
    }
}

