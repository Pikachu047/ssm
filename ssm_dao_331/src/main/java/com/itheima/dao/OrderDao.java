package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    /**
     * 查询全部
     * @return
     */
    @Select("select * from orders")
//    @Results 映射多列数据
//    @Result:映射单列数据
//    select :全限类名+ 方法名 == mapperId
    @Results({
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById"))
    })
    List<Order> findAll();

    /**
     * 保存订单
     * @param order
     */
    @Insert("insert into orders values(order_seq.nextval ,#{orderNum}, #{orderTime} ," +
            "#{peopleCount},#{orderDesc}, #{payType} , #{orderStatus} , #{product.id})")
    void save(Order order);
}
