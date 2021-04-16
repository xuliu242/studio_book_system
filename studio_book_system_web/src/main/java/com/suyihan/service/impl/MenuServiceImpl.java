package com.suyihan.service.impl;

import com.suyihan.entity.Menu;
import com.suyihan.entity.QueryMenuCondition;
import com.suyihan.mapper.MenuMapper;
import com.suyihan.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectMenuByCondition(null);
    }

    @Override
    public Menu selectByMenuId(Long menuId) {
        return menuMapper.selectByMenuId(menuId);
    }

    @Override
    public Menu selectMenuByMenuName(String menuName) {
        return menuMapper.selectByMenuName(menuName);
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    @Override
    public int updateMenuById(Menu menu) {
        return menuMapper.updateMenuById(menu);
    }

    @Override
    public List<Menu> selectMenuByCondition(QueryMenuCondition qmc) {

        List<Menu> menusAll = menuMapper.selectMenuByCondition(qmc);//  全部菜单
        List<Menu> firstMenuList=new ArrayList<>();
//        List<Menu> secMenuList=new ArrayList<>();
//        List<Menu> thirdMenuList=new ArrayList<>();
        //筛选第一级目录菜单
        for (Menu menu:menusAll) {
            if (menu.getSyhMenuParentId()==null){
                firstMenuList.add(menu);
            }
        }
        //筛选第二级目录
        for (Menu menu: firstMenuList)
        {
            List<Menu> menuList = menuMapper.selectByParentId(menu.getSyhMenuId());
            menu.setChildren(menuList);
        }
        //获取第三级目录
        for(Menu menu: firstMenuList){
            for (Menu secMenu:menu.getChildren()){
                List<Menu> menuList = menuMapper.selectByParentId(secMenu.getSyhMenuId());
                secMenu.setChildren(menuList);
            }
        }
        return  firstMenuList;
    }

    @Override
    public List<Menu> selectByParentId(Long menuParentId) {
        return menuMapper.selectByParentId(menuParentId);
    }
}
