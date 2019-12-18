package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
      //查询数据
       List<SysUser> userList =  userService.findAll();
      //创建modelandview对象
        ModelAndView modelAndView = new ModelAndView();
      //添加数据
        modelAndView.addObject("userList",userList);
      //指定页面
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public String save(SysUser user){
        userService.save(user);
        return "redirect:/user/findAll";
    }
    @RequestMapping("/isUniqueUsername")
    @ResponseBody
    public String isUniqueUsername(String username){
        boolean b = userService.isUniqueUsername(username);
        return String.valueOf(b);
    }

    /**
     * 根据 id 查询用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/details")
    public ModelAndView details(Integer userId){
        //查询数据
        SysUser user =  userService.findById(userId);
        //创建modelandview对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("user",user);
        //指定页面
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 为用户添加角色的数据回显
     * @param userId
     * @return
     */
    @RequestMapping("/addRoleToUserUI")
    public ModelAndView addRoleToUserUI(Integer userId){
        //查询全部角色数据
        List<Role> roleList = roleService.findAll();
        //查询用户已经拥有的角色数据
        SysUser user = userService.findById(userId);
        StringBuffer sb = new StringBuffer();
        List<Role> roleList1 = user.getRoleList();
        for (Role role : roleList1) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("str",sb.toString());
        modelAndView.addObject("userId",user.getId());
        //指定页面
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     *
     * @param userId 要给那个用户添加角色
     * @param ids 要给用户添加的角色
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer userId,Integer[] ids){
        //保存操作
        userService.addRoleToUser(userId,ids);
        //请求查询全部
        return "redirect:/user/findAll";
    }
}
