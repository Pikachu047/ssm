package com.itheima.service.impl;

import com.itheima.dao.SysUserDao;
import com.itheima.domain.Product;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SysUserDao sysUserDao;
    /**
     * 通过用户名得到用户对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取用户对象（sysuser）
        SysUser sysUser = sysUserDao.findByUsername(username);
        System.out.println(username);
        if(sysUser!= null){
            //创建角色的集合对象
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //创建临时的角色对象
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            //对象添加到集合 中
            authorities.add(authority);
            /**
             * 参数1:用户名
             * 参数2：密码
             * 参数3：角色列表对象
             */
            UserDetails user = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
            return user;
        }
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }
    //保存用户
    @Override
    public void save(SysUser user) {
        String password = user.getPassword();
        //对明文密码进行加密
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        sysUserDao.save(user);
    }
    //判断是否已经存在该用户
    @Override
    public boolean isUniqueUsername(String username) {
        SysUser user =  sysUserDao.findAllByUsername(username);
        //如果user为空 返回true  表示用户名可用
        //如果user不为空 返回false  表示用户名不可用
        return user == null;
    }

    @Override
    public SysUser findById(Integer userId) {
        return sysUserDao.findById(userId);
    }
   //给用户添加角色
    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        //先清空用户已经拥有的角色
        sysUserDao.delRolesFormUser(userId);
        //维护新的关系
        //判断数组是否为空
        if(ids != null){
            for (Integer roleId : ids) {
                sysUserDao.saveRoleToUser(userId,roleId);
            }
        }
    }
}
