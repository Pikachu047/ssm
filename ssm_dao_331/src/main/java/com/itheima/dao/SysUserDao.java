package com.itheima.dao;

import com.itheima.domain.Product;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SysUserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from sys_user where username = #{username} and status = 1")
    SysUser findByUsername(String username);

    /**
     * 查询全部用户
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> findAll();
    @Insert("insert into sys_user values(user_seq.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser user);

    //判断用户名是否已经存在
    @Select("select * from sys_user where username = #{username}")
    SysUser findAllByUsername(String username);


    //根据id查询用户信息
    @Select("select * from sys_user where id = #{userId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList", column = "id",javaType = List.class,
            //查询角色列表
            many=@Many(select="com.itheima.dao.RoleDao.findRoleListByUserId",fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer userId);

    /**
     * 删除用户已有的角色
     * @param userId
     */
    @Delete("delete from sys_user_role where userId = #{userId}")
    void delRolesFormUser(Integer userId);
    //为用户添加新的角色
    @Insert("insert into sys_user_role values(#{param1},#{param2})")
    void saveRoleToUser(Integer userId,Integer roleId);
}
