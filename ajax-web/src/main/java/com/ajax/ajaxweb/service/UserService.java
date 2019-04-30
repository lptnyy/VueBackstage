package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.mapper.UserMapper;
import com.ajax.ajaxweb.util.MapUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
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

    @Override
    public List<User> getUsers(User user, int pageNo, int pageSize) throws Exception{
        Map keys = MapUtil.objectToMap(user);
        keys.put("limit", ((pageNo -1) * pageSize) +","+pageSize);
        return userMapper.getUsers(keys);
    }

    @Override
    public int getUsersCount(User user) throws Exception {
        Map keys = MapUtil.objectToMap(user);
        return userMapper.getUsersCount(keys);
    }

    @Override
    public int updateUserStat(User user) throws Exception {
        return userMapper.updateStat(user.getId(), user.getStat());
    }

    @Override
    public int deleteUser(Integer id) throws Exception {
        return userMapper.deleteUser(id);
    }

}
