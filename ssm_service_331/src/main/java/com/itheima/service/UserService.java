package com.itheima.service;

import com.itheima.domain.Product;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * UserDetailsService:接口提供一个方法 LoadUserByUsername
 */
public interface UserService extends UserDetailsService {
    List<SysUser> findAll();
    //保存操作
    void save(SysUser user);
    //判断是否已经存在该用户
    boolean isUniqueUsername(String username);

    SysUser findById(Integer userId);

    void addRoleToUser(Integer userId, Integer[] ids);
}
