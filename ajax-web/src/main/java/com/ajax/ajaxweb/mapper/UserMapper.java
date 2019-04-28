package com.ajax.ajaxweb.mapper;

import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.Map;

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
}
