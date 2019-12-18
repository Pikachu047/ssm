package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delOne(Integer id) {
        productDao.delOne(id);
    }

    /*删除多个
     */
    @Override
    public void delMany(Integer[] ids) {
      if(ids != null){
          //遍历逐个删除
          for (Integer id : ids) {
              productDao.delOne(id);
          }
      }
    }

    /**
     * 分页助手
     * @param currPage 当前页
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public PageInfo<Product> findByPageHelper(Integer currPage, Integer pageSize) {
        //指定分页参数
        PageHelper.startPage(currPage,pageSize);
        //查询全部
        List<Product> productList = productDao.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(productList,3);
        return pageInfo;
    }
}
