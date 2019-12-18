package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from sys_permission")
    List<Permission> findAll();


    //数据回显
    @Select("select * from sys_permission")
    List<Permission> findAllParentPermission();
    //保存操作
    @Insert("insert into sys_permission values(permission_seq.nextval,#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    /**
     * 根据角色id查询权限列表
     * @param roleId
     * @return
     */
    @Select("select p.*  from sys_role_permission rp, sys_permission p where rp.permissionid = p.id and roleId = #{roleId}")
    public List<Permission> findPermissionListByRoleId(Integer roleId);
}
