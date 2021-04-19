package com.suyihan.mapper;

import com.suyihan.entity.QueryUserRoleCondition;
import com.suyihan.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface UserRoleMapper extends BaseMapper<UserRole> {
    // 根据用户所属角色id查找用户所属角色信息
    @Select("select ur.syh_user_role_id,ur.syh_role_id,r.ROLE_NAME,ur.syh_user_id,u.USER_NAME \n" +
            "from syh_user_role ur\n" +
            "LEFT JOIN syh_role r on ur.syh_role_id=r.syh_role_id\n" +
            "LEFT JOIN syh_user u on ur.syh_user_id=u.syh_user_id\n" +
            "where syh_syh_user_role_id=#{userRoleId}")
    UserRole selectByUserRoleId(Long userRoleId);

    // 根据用户所属角色名查找用户所属角色信息 ???
    List<UserRole> selectByUserRoleName(String userRoleName);

    // 条件查询
    List<UserRole> selectUserRoleByCondition(QueryUserRoleCondition qurc);

    // 根据用户所属角色id查找用户所属角色信息
    int insertUserRole(UserRole userRole);

    // 根据用户所属角色id查找用户所属角色信息
    @Delete("delete from syh_user_role\n" +
            "    where syh_user_role_id = #{userRoleId}")
    int deleteUserRoleById(Long userRoleId);

    // 根据用户所属角色ID更新用户所属角色信息
    int updateUserRoleById(UserRole userRole);

    // 根据用户id查询角色ids
    @Select("SELECT syh_role_id FROM syh_user_role where syh_user_id=#{userId} ORDER BY syh_role_id")
    List<Long> selectRoleIds(Long userId);

    // 根据用户id删除信息
    @Delete("delete from syh_user_role where syh_user_id=#{userId}")
    int deleteByUserId(Long userId);

    // 根据角色id查询用户ids
    @Select("SELECT syh_user_id FROM syh_user_role where syh_role_id=#{userId} ORDER BY syh_user_id")
    List<Long> selectUserIds(Long roleId);
}
