package com.suyihan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suyihan.entity.QueryUserCondition;
import com.suyihan.entity.User;
import com.suyihan.handle.BusinessException;
import com.suyihan.mapper.UserMapper;
import com.suyihan.response.ResultCode;
import com.suyihan.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suyihan.util.MD5Utils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> queryAllStudentByPage(Page page) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectByUserId(Long userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public User selectUserByLoginName(Integer userLoginName) {
        return userMapper.selectUserByLoginName(userLoginName);
    }

    @Override
    public int insertUser(User user) {
        User userByLoginName = userMapper.selectUserByLoginName(user.getSyhUserNumber());
        if (userByLoginName!=null){
            throw new BusinessException(ResultCode.USER_AlREADY_EXISTS_EXCEPTION.getCode(),"用户名已存在");
        }
        String salt = user.getSyhUserName();
        String md5Encryption = MD5Utils.md5Encryption(user.getSyhPassword(), salt);
        String substring = md5Encryption.substring(8, 24);
        user.setSyhPassword(substring);

        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUserById(Long userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public int updateUserById(User user) {
        String salt = user.getSyhUserName();
        String md5Encryption = MD5Utils.md5Encryption(user.getSyhPassword(), salt);
        String substring = md5Encryption.substring(8, 24);
        user.setSyhPassword(substring);
        return userMapper.updateUserById(user);
    }

    @Override
    public int updateUserStatusById(Long userId, Integer userStatus) {
        System.out.println("userId"+userId+",userStatus"+userStatus);
        return userMapper.updateUserStatusById(userId,userStatus);
    }

    @Override
    public List<User> selectUserByCondition(QueryUserCondition queryUserCondition) {
        List<User> userList = userMapper.selectUserByCondition(queryUserCondition);
        return userList;
    }
}
