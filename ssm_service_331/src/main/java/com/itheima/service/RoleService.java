package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    //保存角色
    void save(Role role);

    Role findById(Integer roleId);

    void addPermissionsToRole(Integer roleId, Integer[] ids);
}
