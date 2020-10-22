package com.travel.ssm.service;

import com.travel.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);
}
