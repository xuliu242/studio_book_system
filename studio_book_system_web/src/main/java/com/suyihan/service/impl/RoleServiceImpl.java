package com.suyihan.service.impl;

import com.suyihan.entity.QueryRoleCondition;
import com.suyihan.entity.Role;
import com.suyihan.handle.BusinessException;
import com.suyihan.mapper.RoleMapper;
import com.suyihan.mapper.UserRoleMapper;
import com.suyihan.response.ResultCode;
import com.suyihan.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectRoleByCondition(null);

        return roles;
    }

    @Override
    public Role selectByRoleId(Long roleId) {
        return roleMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Role> selectRoleByRoleName(String roleName) {
        return null;
    }

    @Override
    public int insertRole(Role role) {
        Role selectByRoleName = roleMapper.selectByRoleName(role.getSyhRoleName());
        if (selectByRoleName!=null){
            throw new BusinessException(ResultCode.ROLE_AlREADY_EXISTS_EXCEPTION.getCode(), "角色名已存在");
        }
        return roleMapper.insertRole(role);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        List<Long> userIds = userRoleMapper.selectUserIds(roleId);
        if (userIds!=null){
            throw new BusinessException(ResultCode.ROLE_ASSIGNED_USER_EXCEPTION.getCode(), "角色已分配给用户");
        }
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public int updateRoleById(Role role) {
        return roleMapper.updateRoleById(role);
    }

    @Override
    public List<Role> selectRoleByCondition(QueryRoleCondition condition) {
        return roleMapper.selectRoleByCondition(condition);
    }

    /**
     * @param roleId
     * @param roleHidden
     * @return
     */
    @Override
    public int updateRoleStatusById(Long roleId, Integer roleHidden) {
        System.out.println("roleId"+roleId+",roleHidden"+roleHidden);
        return roleMapper.updateRoleStatusById(roleId, roleHidden);
    }
}
