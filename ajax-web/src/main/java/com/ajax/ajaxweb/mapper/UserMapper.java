package com.ajax.ajaxweb.mapper;

import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import java.util.List;

public interface UserMapper {

    /**
     * 获取用户信息
     * @param keys
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "getUser")
    User getUser(@Param(value = "keys") Map keys);

    /**
     * 登录次数登录时间 添加登录token
     * @param id
     * @return
     */
    @Update("update user set loginNum = loginNum + 1,lastLloginTime = now(),token =#{token} where id=#{id}")
    int loginNum(@Param(value = "id") Integer id,@Param(value = "token") String token);

    /**
     * 获取用户信息
     * @param keys
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "getUsers")
    List<User> getUsers(@Param(value = "keys") Map keys);

    /**
     * 获取用户信息数
     * @param keys
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "getUsersCount")
    int getUsersCount(@Param(value = "keys") Map keys);

    /**
     * 更新用户状态
     * @param id
     * @param stat
     * @return
     */
    @Update("update user set stat = #{stat} where id=#{id}")
    int updateStat(@Param(value = "id") Integer id,@Param(value = "stat") Integer stat);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    int deleteUser(Integer id);

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    @Insert("insert into user(userName,roleId,userPass,loginNum,token,stat) values(#{userName},#{roleId},#{userPass},#{loginNum},#{token},#{stat})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set userPass=#{userPass} where id=#{id}")
    int updateUser(User user);
}
