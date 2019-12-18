package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("/findAll1")
    public ModelAndView findAll1(){
        //获取数据
        List<Product> productList =  productService.findAll();
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("productList",productList);
        //指定页面
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:/product/findAll";
    }
    @RequestMapping("/updateUI")
    public ModelAndView saveUI(Integer id){
        //获取数据
        Product product = productService.findById(id);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("product",product);
        //指定页面
        modelAndView.setViewName("product-update");
        return modelAndView;
    }
    @RequestMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/product/findAll";
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @RequestMapping("/delOne")
    public String delOne(Integer id){
        productService.delOne(id);
        return "redirect:/product/findAll";
    }

    /**
     * 删除多个
     * @param ids
     * @return
     */
    @RequestMapping("/delMany")
    public String delMany(Integer[] ids){
        productService.delMany(ids);
        return "redirect:/product/findAll";
    }

    /**
     * 分页助手查询全部
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(value = "currPage",required = false,defaultValue = "1") Integer currPage,
            @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize
            ){
        //准备数据
        PageInfo<Product> pageInfo = productService.findByPageHelper(currPage, pageSize);
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //添加数据
        modelAndView.addObject("pageInfo",pageInfo);
        //指定页面
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
