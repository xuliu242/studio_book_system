package com.suyihan.mapper;

import com.suyihan.entity.QueryRoleCondition;
import com.suyihan.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    //根据角色id查找角色信息
    @Select("select * from syh_role where syh_role_id=#{roleId}")
    Role selectByRoleId(Long roleId);

    //根据角色名查找角色信息
    @Select("select * from syh_role where syh_role_name=#{roleName}")
    Role selectByRoleName(String roleName);

    //条件查询
    List<Role> selectRoleByCondition(QueryRoleCondition qrc);

    //添加角色数据

    int insertRole(Role role);

    //根据角色ID删除角色信息
    @Delete("delete from syh_role where syh_role_id=#{roleId}")
    int deleteRoleById(Long roleId);

    //根据角色ID更新角色信息
    int updateRoleById(Role role);
//    //根据角色id查找角色信息
//    int selectByRoleId(Role role);

    /**
     * 根据角色id更新role状态
     * @param roleId
     * @param roleHidden
     * @return
     */
    @Update("update syh_role set syh_role_status=#{roleHidden} where syh_role_id=#{roleId}")
    int updateRoleStatusById(@Param("roleId") Long roleId, @Param("roleHidden") Integer roleHidden);


}
