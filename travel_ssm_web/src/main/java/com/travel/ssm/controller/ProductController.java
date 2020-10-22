package com.travel.ssm.controller;

import com.travel.ssm.domain.Product;
import com.travel.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询全部产品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList", products);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll.do";
    }
}
