package com.suyihan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suyihan.entity.QueryUserCondition;
import com.suyihan.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from syh_user ")
    public IPage<User> queryAllUserByPage(Page page);
    //    查询所有用户信息
    public List<User> selectAll();
    //    根据用户ID查询用户信息
    public User selectByUserId(Long userId);
    //    通过用户名查询用户信息(此处修改为用户number)
    public User selectUserByLoginName(Integer userLoginName);
    //    添加用户数据
    public int insertUser(User user);
    //    根据用户ID删除用户信息
    @Delete("delete from syh_user where syh_user_id=#{userId}")
    public int deleteUserById(Long userId);
    //    根据用户ID更新用户信息
    public int updateUserById(User user);
    //    根据用户ID更新用户状态
    @Update("update syh_user set syh_status=#{userStatus} where syh_user_id=#{userId}")
    public int updateUserStatusById(@Param("userId") Long userId, @Param("userStatus")Integer userStatus);
    public List<User> selectUserByCondition(QueryUserCondition queryUserCondition);
}
