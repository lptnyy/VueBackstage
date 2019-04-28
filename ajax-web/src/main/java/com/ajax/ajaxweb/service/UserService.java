package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.mapper.UserMapper;
import com.ajax.ajaxweb.util.MapUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserService implements IUserService{

    @Resource
    UserMapper userMapper;

    @Override
    public User getUser(User user) throws Exception{
        Map keys = MapUtil.objectToMap(user);
        return userMapper.getUser(keys);
    }

    @Override
    public int setLoginInfo(User user){
        return userMapper.loginNum(user.getId(), user.getToken());
    }
}
