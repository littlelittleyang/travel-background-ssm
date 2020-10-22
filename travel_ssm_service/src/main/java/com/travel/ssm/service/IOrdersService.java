package com.travel.ssm.service;

import com.travel.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int size);

    Orders findById(String ordersId);
}
