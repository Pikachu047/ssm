package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        //查询数据
        List<Role> roleList =  roleService.findAll();
        //创建modelandview对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("roleList",roleList);
        //指定页面
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    /**
     *添加权限到角色的数据回显
     * @param roleId
     * @return
     */
    @RequestMapping("/addPermissionsToRoleUI")
    public ModelAndView addPermissionsToRoleUI(Integer roleId){
        //查询数据
        //所有权限
        List<Permission> permissionList = permissionService.findAll();
        //查询已经拥有的权限
       Role role =  roleService.findById(roleId);
       //将已经拥有的权限拼接成一个字符串
        StringBuffer sb = new StringBuffer();
        List<Permission> permissionList1 = role.getPermissionList();
        for (Permission permission : permissionList1) {
            sb.append(",");
            sb.append(permission.getId());
            sb.append(",");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("roleId",role.getId());
        modelAndView.addObject("str",sb.toString());
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(Integer roleId,Integer[] ids){
        //保存操作
        roleService.addPermissionsToRole(roleId,ids);
        //请求查询全部
        return "redirect:/role/findAll";
    }
}
