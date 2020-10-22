package com.travel.ssm.dao;

import com.travel.ssm.domain.Role;
import com.travel.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.travel.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUserName(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email}, " +
            "#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.travel.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in " +
            "(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role (userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

}
