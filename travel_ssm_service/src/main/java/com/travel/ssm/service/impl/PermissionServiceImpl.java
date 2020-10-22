package com.travel.ssm.service.impl;

import com.travel.ssm.dao.IPermissionDao;
import com.travel.ssm.domain.Permission;
import com.travel.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }
}
