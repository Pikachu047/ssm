package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {

    //查询全部
    @Select("select * from sys_role")
    List<Role> findAll();
    //保存角色
    @Insert("insert into sys_role values(role_seq.nextval, #{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据userId获取角色列表
     * @param userId
     * @return
     */
    @Select("select r.* from sys_user_role ur, sys_role r where r.id = ur.roleId and userId = #{userId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "permissionList", column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionListByRoleId",fetchType = FetchType.LAZY))
    })
    public List<Role> findRoleListByUserId(Integer userId);

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    @Select("select * from sys_role where id = #{roleId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "permissionList", column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionListByRoleId",fetchType = FetchType.LAZY))
    })
    Role findById(Integer roleId);

    /**
     * 删除该角色所有的权限
     * @param roleId
     */
    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void delPermissionsFromRole(Integer roleId);

    /**
     * 给角色添加新的权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into sys_role_permission values(#{param2},#{param1})")
    void addPermissionToRole(Integer roleId, Integer permissionId);
}
