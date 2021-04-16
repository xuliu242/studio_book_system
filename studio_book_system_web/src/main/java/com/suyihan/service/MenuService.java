package com.suyihan.service;

import com.suyihan.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suyihan.entity.QueryMenuCondition;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
public interface MenuService extends IService<Menu> {
    // 查询所有菜单信息
    List<Menu> selectAll();
    // 根据菜单ID查询菜单信息
    Menu selectByMenuId(Long menuId);
    // 通过菜单名查询菜单信息
    Menu selectMenuByMenuName(String menuName);
    // 添加菜单数据
    int insertMenu(Menu menu);
    // 根据菜单ID删除菜单信息
    int deleteMenuById(Long menuId);
    // 根据菜单ID更新菜单信息
    int updateMenuById(Menu menu);
    // 条件查询
    List<Menu> selectMenuByCondition(QueryMenuCondition qmc);
    List<Menu> selectByParentId(Long menuParentId);
}
