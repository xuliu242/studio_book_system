package com.suyihan.service;

import com.suyihan.entity.QueryUserRoleCondition;
import com.suyihan.entity.Role;
import com.suyihan.entity.UserRole;
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
public interface UserRoleService extends IService<UserRole> {
    List<UserRole> selectAll();     //查询所有用户所属角色信息
    UserRole selectByuserRoleId();  //根据用户所属角色ID查询用户所属角色信息
    UserRole selectuserRoleByuserRoleName();    //通过用户所属角色名查询用户所属角色信息
    int insertuserRole(UserRole userRole);
    int updateuserRoleById(UserRole userRole);
    int deleteuserRoleById(Integer userRoleId);
    List<UserRole> selectByQueryUserRoleCondition(QueryUserRoleCondition qurc);
    List<Integer> selectRoleIds(Integer userId);    //根据用户id查询角色ids
    Boolean doAssignRoles(Integer userId,Integer[] roleIds);
    List<Role> selectRolesByUserId(Integer userId);
}
