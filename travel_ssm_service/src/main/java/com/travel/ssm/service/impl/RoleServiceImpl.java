package com.travel.ssm.service.impl;

import com.travel.ssm.dao.IRoleDao;
import com.travel.ssm.domain.Permission;
import com.travel.ssm.domain.Role;
import com.travel.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRole(String roleId) {
        //从user_role表中删除
        roleDao.deleteFromUsers_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleIf(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
