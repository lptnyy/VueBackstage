package com.ajax.ajaxweb.mapper;
import com.ajax.ajaxweb.entity.RoleFunctions;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface RoleFunctionsMapper {

    /**
     * 添加一条信息
     * @param functions
     * @return
     */
    @Insert("insert into role_functions(roleId,functionId) values (#{roleId},#{functionId})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int add(RoleFunctions functions);

    /**
     * 获取
     * @return
     */
    @Select("select t.id,t.roleId,t.functionId,t.createTime from role_functions t where t.roleId=#{roleId}")
    List<RoleFunctions> getFindList(@Param(value = "roleId") Integer roleId);

    /**
     * 删除指定角色下的权限
     * @return
     */
    @Delete("delete from role_functions where roleId=#{roleId}")
    int del(@Param(value = "roleId") Integer roleId);

    /**
     * 验证权限是否被使用
     * @param functionId
     * @return
     */
    @Select("select count(*) from role_functions y,functions s where y.functionId = s.id and (y.functionId = #{functionId} or s.parentId = #{functionId})")
    int checkRFCount(@Param(value = "functionId") Integer functionId);
}
