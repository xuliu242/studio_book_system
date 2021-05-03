package com.suyihan.mapper;

import com.suyihan.entity.Menu;
import com.suyihan.entity.RoleMenu;
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
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    //根据角色权限id查找角色权限信息
    @Select("select * from syh_role_menu where syh_role_menu_id=#{roleMenuId}")
    RoleMenu selectByRoleMenuId(Long roleMenuId);

    //添加角色权限数据
    int insertRoleMenu(RoleMenu roleMenu);

    //根据角色权限ID删除角色权限信息
    @Delete("delete from syh_role_menu where syh_role_menu_id=#{roleMenuId}")
    int deleteRoleMenuById(Long roleMenuId);

    //根据角色ID删除角色权限信息
    @Delete("delete from syh_role_menu where syh_role_id=#{roleId}")
    int deleteRoleMenuByRoleId(Long roleId);

    //根据角色id查找权限信息
    @Select("SELECT * from syh_menu where syh_menu_id in \n" +
            "(SELECT syh_menu_id FROM syh_role_menu where syh_role_id = #{roleId})\n")
    List<Menu> selectByRoleId(Long roleId);

    //根据用户id 查询菜单列表
    @Select("SELECT * FROM syh_menu where syh_menu_id in \n" +
            "(select DISTINCT syh_menu_id from syh_role_menu rm where rm.syh_role_id in \n" +
            "(select ur.syh_role_id from syh_user_role ur where ur.syh_role_id = #{userId})\n" +
            ")ORDER BY  syh_menu_queue")
    List<Menu> selectMenuByUserId(Long userId);
}
