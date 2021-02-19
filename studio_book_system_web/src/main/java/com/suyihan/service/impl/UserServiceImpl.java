package com.suyihan.service.impl;

import com.suyihan.entity.User;
import com.suyihan.mapper.UserMapper;
import com.suyihan.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Suyihan
 * @since 2021-02-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
