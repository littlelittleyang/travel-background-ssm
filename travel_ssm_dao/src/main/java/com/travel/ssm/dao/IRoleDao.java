package com.travel.ssm.dao;

import com.travel.ssm.domain.Permission;
import com.travel.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IRoleDao {

    @Select("select * from role where id in " +
            "(select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(property = "permissions", column = "id", javaType = List.class,
            many = @Many(select = "com.travel.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String usreId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissions", column = "id", javaType = List.class,
            many = @Many(select = "com.travel.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String roleId);

    @Delete("delete from users_role where roleId = #{roleId}")
    void deleteFromUsers_RoleByRoleId(String roleId);

    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteFromRole_PermissionByRoleIf(String roleId);

    @Delete("delete from role where id = #{roleId}")
    void deleteRoleById(String roleId);

     @Select("select * from permission where id not in " +
             "(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (permissionId, roleId) values (#{permissionId}, #{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
