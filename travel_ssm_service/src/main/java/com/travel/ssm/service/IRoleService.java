package com.travel.ssm.service;

import com.travel.ssm.domain.Permission;
import com.travel.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    void deleteRole(String roleId);

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
