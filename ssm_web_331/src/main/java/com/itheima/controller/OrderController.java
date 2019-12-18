package com.itheima.controller;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
       List<Order> orderList = orderService.findAll();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("orderList",orderList);
       modelAndView.setViewName("order-list");
       return modelAndView;
    }
    /**
     * 添加订单 产品数据回显
     */
    @RequestMapping("/addUI")
    public ModelAndView addUI(){
        //查询数据
        List<Product> productList = productService.findAll();
        //创建modelandview对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("productList",productList);
        //设置跳转页面
        modelAndView.setViewName("order-add");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Order order){
        orderService.save(order);
        return "redirect:/order/findAll";
    }
}
