package com.travel.ssm.controller;

import com.travel.ssm.domain.Permission;
import com.travel.ssm.domain.Role;
import com.travel.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id") String roleId) {
        roleService.deleteRole(roleId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        List<Permission> oteherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("roleId", roleId);
        mv.addObject("permissionList", oteherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId,
                                @RequestParam(name = "ids") String[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findById.do?id=" + roleId;
    }
}
