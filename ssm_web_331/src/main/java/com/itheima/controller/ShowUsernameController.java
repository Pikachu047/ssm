package com.itheima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * 显示用户名
 */
public class ShowUsernameController {
    @RequestMapping("/showUsername")
    public void showUsername(HttpServletRequest request){
        //获取session对象
        HttpSession session = request.getSession();
        //从session域中获取登录信息
        //从session域中获取所有的属性名
        //枚举类型：遍历枚举类型
        Enumeration attributeNames = session.getAttributeNames();
        //attributeNames.hasMoreElements() 判断是否有更多的元素
        //attributeNames.nextElement() 获取枚举中下一个元素
        while (attributeNames.hasMoreElements()){
            System.out.println(attributeNames.nextElement());
        }
        //SPRING_SECURITY_CONTEXT : 存储用户登录信息的session中的名称
        Object spring_security_context = session.getAttribute("SPRING_SECURITY_CONTEXT");
        //获取的是SecurityContext对象，是安全框架的上下文对象
        SecurityContext securityContext = (SecurityContext) spring_security_context;
        // 获取认证信息
        Authentication authentication = securityContext.getAuthentication();
        //Principal: 翻译：重要的 -- 获取的是重要信息 -- 用户详情(UserDetails)
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        //获取用户名
        String username = user.getUsername();
        System.out.println(username);

    }
}
