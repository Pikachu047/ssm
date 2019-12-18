package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
    //根据id查询权限
    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    //添加权限到角色
    @Override
    public void addPermissionsToRole(Integer roleId, Integer[] ids) {
        //删除所有关系
        roleDao.delPermissionsFromRole(roleId);
        //维护新的关系
        if (ids !=null){
            for (Integer permissionId : ids) {
                roleDao.addPermissionToRole(roleId,permissionId);
            }
        }
    }
}
