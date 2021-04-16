package com.suyihan.service;

import com.suyihan.entity.Menu;
import com.suyihan.entity.Role;
import com.suyihan.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
public interface RoleMenuService extends IService<RoleMenu> {
    RoleMenu selectByRoleMenuId(Long roleMenuId);
    Boolean insertRoleMenu(Long roleId, List<Long> menuIds);
    int deleteRoleMenuById(Long roleMenuId);
    List<Menu> selectByRoleId(Long roleId);
    List<Menu> selectMenuByUserId(Long userId);
    public List<Menu> findMenuByRoles(List<Role> roles);
}
