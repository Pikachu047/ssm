package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(Integer id);

    void update(Product product);

    void delOne(Integer id);

    void delMany(Integer[] ids);

    PageInfo<Product> findByPageHelper(Integer currPage,Integer pageSize);
}
