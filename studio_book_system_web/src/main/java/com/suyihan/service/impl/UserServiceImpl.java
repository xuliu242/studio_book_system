package com.suyihan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suyihan.entity.QueryUserCondition;
import com.suyihan.entity.User;
import com.suyihan.mapper.UserMapper;
import com.suyihan.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> queryAllStudentByPage(Page page) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectByUserId(Long userId) {
        return null;
    }

    @Override
    public User selectUserByLoginName(String userLoginName) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        return 0;
    }

    @Override
    public int updateUserStatusById(Long userId, String userStatus) {
        return 0;
    }

    @Override
    public List<User> selectUserByCondition(QueryUserCondition queryUserCondition) {
        return null;
    }
}
