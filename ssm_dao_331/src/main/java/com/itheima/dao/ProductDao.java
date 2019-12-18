package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductDao {

    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product values(#{id} , #{productNum},#{productName},#{cityName},#{departureTime}, #{productPrice},#{productDesc}, #{productStatus})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Integer.class,
    statement = "select product_seq.nextval from dual")
    void save(Product product);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select p.*,to_char(departureTime,'yyyy-mm-dd hh24:mi') departureTimeStr from product p where id = #{abc}")
    Product findById(Integer id);

    /**
     * 更新操作
     * @param product
     */
    @Update("update product set productNum=#{productNum},productName=#{productName}," +
            "cityName=#{cityName},departureTime=#{departureTime}, productPrice=#{productPrice}," +
            "productDesc=#{productDesc}, productStatus=#{productStatus} where id = #{id}")
    void update(Product product);

    /*
    删除一个
     */
    @Delete("delete from product where id= #{abc}")
    void delOne(Integer id);
}
