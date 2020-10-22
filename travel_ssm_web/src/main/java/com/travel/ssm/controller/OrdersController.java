package com.travel.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.travel.ssm.domain.Orders;
import com.travel.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "size", defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        PageInfo pageInfo = new PageInfo<>(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String ordersId) {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return  mv;
    }
}
