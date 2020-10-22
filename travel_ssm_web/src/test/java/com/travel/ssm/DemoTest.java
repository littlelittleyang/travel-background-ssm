package com.travel.ssm;

import com.travel.ssm.dao.IOrdersDao;
import com.travel.ssm.dao.IProductDao;
import com.travel.ssm.domain.Orders;
import com.travel.ssm.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class DemoTest {

    @Test
    public void productDao() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        IProductDao productDao = ac.getBean(IProductDao.class);
        List<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void ordersTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrdersDao ordersDao = ac.getBean(IOrdersDao.class);
        List<Orders> ordersList = ordersDao.findAll();
        for (Orders orders : ordersList) {
            System.out.println(orders);
        }
    }

    @Test
    public void encodePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
