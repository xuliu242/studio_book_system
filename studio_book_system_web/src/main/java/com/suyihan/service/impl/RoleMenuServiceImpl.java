package com.suyihan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suyihan.entity.Menu;
import com.suyihan.entity.Role;
import com.suyihan.entity.RoleMenu;
import com.suyihan.mapper.MenuMapper;
import com.suyihan.mapper.RoleMenuMapper;
import com.suyihan.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Suyihan
 * @since 2021-04-16
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public RoleMenu selectByRoleMenuId(Long roleMenuId) {
        return roleMenuMapper.selectByRoleMenuId(roleMenuId);
    }

    /**
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public Boolean insertRoleMenu(Long roleId, List<Long> menuIds) {
        //删除已分配菜单权限
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
//        添加新分配的菜单权限
        for (int i = 0; i < menuIds.size(); i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setSyhRoleId(roleId);
            roleMenu.setSyhMenuId(menuIds.get(i));
            int insertRoleMenu = roleMenuMapper.insertRoleMenu(roleMenu);
            if (insertRoleMenu <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int deleteRoleMenuById(Long roleMenuId) {
        return roleMenuMapper.deleteRoleMenuById(roleMenuId);
    }

    /**
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> selectByRoleId(Long roleId) {
        List<Menu> menusAll = roleMenuMapper.selectByRoleId(roleId);
        return menusAll;
    }

    @Override
    public List<Menu> selectMenuByUserId(Long userId) {
        List<Menu> menuAll = roleMenuMapper.selectMenuByUserId(userId);
        List<Menu> menuList = new ArrayList<>();
        for (Menu menu : menuAll) {
            if (menu.getSyhMenuParentId() == null) {
                menuList.add(menu);
            }
        }
        //筛选第二级
        for (Menu firstMenu:menuList){
            List<Menu> secMenuList=new ArrayList<>();
            for (Menu menu:menuAll ){
                if (menu.getSyhMenuParentId()==firstMenu.getSyhMenuId()){
                    secMenuList.add(menu);
                }
            }
            firstMenu.setChildren(secMenuList);
        }
        return menuList;
    }

    @Override
    public List<Menu> findMenuByRoles(List<Role> roles) {
        List<Menu> menus=new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            Set<Long> menuIds=new HashSet<>();//存放用户的菜单id
            List<RoleMenu> roleMenus;
            for (Role role : roles) {
                //根据角色ID查询权限ID
                QueryWrapper<RoleMenu> roleMenuQueryWrapper=new QueryWrapper<>();
                roleMenuQueryWrapper.eq("ROLE_ID",role.getSyhRoleId());
                roleMenus=roleMenuMapper.selectList(roleMenuQueryWrapper);

                if(!CollectionUtils.isEmpty(roleMenus)){
                    for (RoleMenu roleMenu : roleMenus) {
                        menuIds.add(roleMenu.getSyhMenuId());
                    }
                }
            }
            if(!CollectionUtils.isEmpty(menuIds)){
                for (Long menuId : menuIds) {
                    //该用户所有的菜单
                    Menu menu = menuMapper.selectByMenuId(menuId);
                    if(menu!=null){
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }
}
