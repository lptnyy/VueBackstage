package com.ajax.ajaxweb.service;

import com.ajax.ajaxweb.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {
    User getUser(User user) throws Exception;
    @Transactional
    int setLoginInfo(User user) throws Exception;
    List<User> getUsers(User user, int pageNo,int pageSize) throws Exception;
    int getUsersCount(User user) throws Exception;
    @Transactional
    int updateUserStat(User user) throws Exception;
    @Transactional
    int deleteUser(Integer id) throws Exception;
    @Transactional
    int addUser(User user, String roles) throws Exception;
}
