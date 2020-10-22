package com.travel.ssm.controller;

import com.travel.ssm.domain.Role;
import com.travel.ssm.domain.UserInfo;
import com.travel.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList", users);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userId) {
        ModelAndView mv = new ModelAndView();
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("userId", userId);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,
                                @RequestParam(name = "ids") String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);

        return "redirect:findById.do?id=" + userId;
    }
}
