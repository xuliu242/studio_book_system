package com.suyihan.service;

import com.suyihan.entity.QueryRoleCondition;
import com.suyihan.entity.Role;
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
public interface RoleService extends IService<Role> {
    //查询所有角色信息
    List<Role> selectAll();
    //根据角色ID查询角色信息
    Role selectByRoleId(Long roleId);
    //通过角色名查询角色信息
    List<Role> selectRoleByRoleName(String roleName);
    //添加角色数据
    int insertRole(Role role);
    //根据角色ID删除角色信息
    int deleteRoleById(Long roleId);
    //根据角色ID更新角色信息
    int updateRoleById(Role role);
    // 条件查询
    List<Role> selectRoleByCondition(QueryRoleCondition condition);

    /**
     * 根据角色id更新角色状态
     * @param roleId
     * @param roleHidden
     * @return
     */
    int updateRoleStatusById(Long roleId,Integer roleHidden);
}
