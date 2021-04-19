package com.suyihan.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 账号
     */
    private Integer syhUserNumber;
    /**
     * 菜单路径
     */
    private Set<String> url;
    /**
     * 权限
     */
    private Set<String> perms;
    /**
     * 角色
     */
    private List<String> roles;



}
