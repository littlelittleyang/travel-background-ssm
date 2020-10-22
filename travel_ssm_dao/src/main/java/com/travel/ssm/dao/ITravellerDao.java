package com.travel.ssm.dao;

import com.travel.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravellerDao {

    @Select("select * from traveller where id in " +
            "(select travellerId from order_traveller where orderId= #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
