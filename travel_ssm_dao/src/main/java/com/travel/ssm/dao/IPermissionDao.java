package com.travel.ssm.dao;

import com.travel.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in " +
            "(select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName, url) values (#{permissionName}, #{url})")
    void save(Permission permission);

    @Select("select * from permission where id = #{id}")
    Permission findById(String id);

    @Delete("delete from role_permission where permissionId = #{id}")
    void deleteFromRole_Permission(String id);

    @Delete("delete from permission where id = #{id}")
    void deleteById(String id);
}
