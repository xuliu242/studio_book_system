package com.suyihan.service.impl;

import com.suyihan.entity.QueryUserRoleCondition;
import com.suyihan.entity.Role;
import com.suyihan.entity.UserRole;
import com.suyihan.mapper.UserRoleMapper;
import com.suyihan.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> selectAll() {
        return null;
    }

    @Override
    public UserRole selectByuserRoleId() {
        return null;
    }

    @Override
    public UserRole selectuserRoleByuserRoleName() {
        return null;
    }

    @Override
    public int insertuserRole(UserRole userRole) {
        return 0;
    }

    @Override
    public int updateuserRoleById(UserRole userRole) {
        return 0;
    }

    @Override
    public int deleteuserRoleById(Long userRoleId) {
        return 0;
    }

    @Override
    public List<UserRole> selectByQueryUserRoleCondition(QueryUserRoleCondition qurc) {
        return null;
    }

    @Override
    public List<Long> selectRoleIds(Long userId) {
        return null;
    }

    @Override
    public Boolean doAssignRoles(Long userId, Long[] roleIds) {
        return null;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return null;
    }
}
