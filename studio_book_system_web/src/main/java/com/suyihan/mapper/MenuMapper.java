package com.suyihan.mapper;

import com.suyihan.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyihan.entity.QueryMenuCondition;
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
public interface MenuMapper extends BaseMapper<Menu> {
    //  根据菜单id查找菜单信息
    @Select("select * from syh_menu where syh_menu_id=#{menuId}")
    Menu selectByMenuId(Long menuId);

    /**
     * 根据父级id查询子菜单
     * @param menuParentId
     * @return
     */
    @Select("SELECT * FROM syh_menu where syh_menu_parent_id=#{menuParentId}")
    List<Menu> selectByParentId(Long menuParentId);
    @Select("SELECT * FROM syh_menu where syh_menu_parent_id is null")
    List<Menu> selectFirstMenu();

    //  根据菜单名查找菜单信息
    @Select("select * from syh_menu where syh_menu_name=#{menuName}")
    Menu selectByMenuName(String menuName);

    //  条件查询
    List<Menu> selectMenuByCondition(QueryMenuCondition qmc);

    //  添加菜单数据
    int insertMenu(Menu menu);

    //  根据菜单ID删除菜单信息
    @Delete("delete from syh_menu where syh_menu_id=#{menuId}")
    int deleteMenuById(Long menuId);

    //  根据菜单ID更新菜单信息
    int updateMenuById(Menu menu);
}
