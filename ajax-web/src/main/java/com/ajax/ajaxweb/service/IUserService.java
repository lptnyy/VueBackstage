package com.ajax.ajaxweb.service;

import com.ajax.ajaxweb.entity.User;
import java.util.List;

public interface IUserService {
    User getUser(User user) throws Exception;
    int setLoginInfo(User user) throws Exception;
    List<User> getUsers(User user, int pageNo,int pageSize) throws Exception;
    int getUsersCount(User user) throws Exception;
    int updateUserStat(User user) throws Exception;
    int deleteUser(Integer id) throws Exception;
}
