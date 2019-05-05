package com.ajax.ajaxweb.mapper;

import com.ajax.ajaxweb.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper {

    @Select("select t.id,t.userId,t.roleId,t.createTime from user_role t where t.userId=#{userId}")
    List<UserRole> getUserRoleList(@Param(value = "userId") Integer userId);

    @Delete("delete from user_role where userId=#{userId}")
    int deleteUserRole(@Param(value = "userId") Integer userId);

    @Insert("insert into user_role(userId,roleId) values (#{userId},#{roleId})")
    int addUserRole(UserRole userRole);
}
