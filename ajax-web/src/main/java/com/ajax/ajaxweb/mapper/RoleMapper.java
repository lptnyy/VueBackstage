package com.ajax.ajaxweb.mapper;

import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.mapper.provider.RoleProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    /**
     * 获取权限角色信息
     * @param keys
     * @return
     */
    @SelectProvider(type = RoleProvider.class, method = "getRole")
    Role getRole(@Param(value = "keys") Map keys);

    /**
     * 查询列表数据
     * @param keys
     * @return
     */
    @SelectProvider(type = RoleProvider.class, method = "getRole")
    List<Role> getRoles(@Param(value = "keys") Map keys);

    /**
     * 查询数据数量
     * @param keys
     * @return
     */
    @SelectProvider(type = RoleProvider.class, method = "getRoleCount")
    int getRolesCount(@Param(value = "keys") Map keys);

    /**
     * delete role
     * @param id
     * @return
     */
    @Delete("delete from role where id=#{id}")
    int deleteRoles(@Param(value = "id") Integer id);

    /**
     * update stat role
     * @param id
     * @param stat
     * @return
     */
    @Update("update role set stat=#{stat} where id=#{id}")
    int updateRolesStat(@Param(value = "id") Integer id,@Param(value = "stat") Integer stat);

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Insert("insert into role(roleName) values(#{roleName})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addRole(Role role);

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Update("update role set roleName=#{roleName} where id=#{id}")
    int updateRole(Role role);
}
