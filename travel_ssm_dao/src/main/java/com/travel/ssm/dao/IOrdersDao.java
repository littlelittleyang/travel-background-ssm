package com.travel.ssm.dao;

import com.travel.ssm.domain.Member;
import com.travel.ssm.domain.Orders;
import com.travel.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(property = "product", column = "productId", javaType = Product.class,
            one = @One(select = "com.travel.ssm.dao.IProductDao.findById"))

    })
    List<Orders> findAll();

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.travel.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,
                    one = @One(select = "com.travel.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class,
                    many = @Many(select = "com.travel.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId);
}
