package com.ajax.ajaxweb.mapper;
import com.ajax.ajaxweb.entity.Function;
import com.ajax.ajaxweb.mapper.provider.FunctionProvider;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface FunctionMapper {

    /**
     * 多条件查询数据
     * @param keys
     * @return
     */
    @SelectProvider(type = FunctionProvider.class, method = "getList")
    List<Function> getList(@Param(value = "keys") Map keys);

    /**
     * 查询数据条数
     * @param keys
     * @return
     */
    @SelectProvider(type = FunctionProvider.class, method = "getSqlCount")
    int getCount(@Param(value = "keys") Map keys);

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    @SelectProvider(type = FunctionProvider.class, method = "getFunction")
    Function getFunction(@Param(value = "id") Integer id);

    /**
     * 插入多条数据
     * @param function
     * @return
     */
    @Insert("insert into functions(name,type,url,parentId) values (#{name},#{type},#{url},#{parentId})")
    int add(Function function);

    /**
     * 删除权限信息
     * @param id
     * @return
     */
    @Update("update functions set isDel = 1 where id=#{id} or parentId=#{id}")
    int delete(@Param(value = "id") Integer id);

    /**
     * 修改状态
     * @param id
     * @param stat
     * @return
     */
    @Update("update functions set stat = #{stat} where id=#{id}")
    int updateStat(@Param(value = "id") Integer id,@Param(value = "stat") Integer stat);

    /**
     * 查询权限信息
     * @return
     */
    @Select("select x.*,y.name as parentName,y.url as parentUrl from user t, user_role s, role e,role_functions w, functions x,functions y " +
            " where t.id = s.userId " +
            " and s.roleId = w.roleId " +
            " and s.roleId = e.id " +
            " and e.id = w.roleId " +
            " and w.functionId = x.id " +
            " and x.parentId = y.id " +
            " and x.stat = 0 " +
            " and y.stat = 0 " +
            " and e.stat = 0 " +
            " and x.isDel = 0 " +
            " and y.isDel = 0 " +
            " and t.stat = 0 " +
            " and t.id = #{userId} " +
            " order by x.parentId, x.id")
    List<Function> getLeftUserFunctions(@Param(value = "userId") Integer userId);
}