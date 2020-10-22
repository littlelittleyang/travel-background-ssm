package com.travel.ssm.controller;

import com.travel.ssm.domain.Permission;
import com.travel.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission.do")
    public String deletePermission(String id) {
        permissionService.deleteById(id);
        return "redirect:findAll.do";

    }
}
