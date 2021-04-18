package com.suyihan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suyihan.entity.QueryUserCondition;
import com.suyihan.entity.User;
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
public interface UserService extends IService<User> {
    public IPage<User> queryAllStudentByPage(Page page);
    // 查询所有用户信息
    List<User> selectAll();
    // 根据用户ID查询用户信息
    User selectByUserId(Long userId);
    // 通过用户名查询用户信息
    User selectUserByLoginName(String userLoginName);
    // 添加用户数据
    int insertUser(User user);
    // 根据用户ID删除用户信息
    int deleteUserById(Long userId);
    // 根据用户ID更新用户信息
    int updateUserById(User user);
    // 根据用户ID更新用户状态
    int updateUserStatusById(Long userId,String userStatus);
    // 条件查询
    List<User> selectUserByCondition(QueryUserCondition queryUserCondition);
}
