package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.entity.UserRole;
import com.ajax.ajaxweb.mapper.UserMapper;
import com.ajax.ajaxweb.mapper.UserRoleMapper;
import com.ajax.ajaxweb.util.MapUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService{

    @Resource
    UserMapper userMapper;

    @Resource
    UserRoleMapper userRoleMapper;

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
        userRoleMapper.deleteUserRole(id);
        return userMapper.deleteUser(id);
    }

    @Override
    public int addUser(User user, String roles) throws Exception {
        user.setToken("");
        user.setLoginNum(0);
        user.setStat(0);
        user.setRoleId(0);
        String[] funciton = roles.split(",");
        userMapper.addUser(user);
        for(String fun:funciton) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(Integer.valueOf(fun));
            userRole.setUserId(user.getId());
            userRoleMapper.addUserRole(userRole);
        }
        return 0;
    }

}
