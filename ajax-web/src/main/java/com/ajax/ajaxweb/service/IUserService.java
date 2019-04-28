package com.ajax.ajaxweb.service;

import com.ajax.ajaxweb.entity.User;

public interface IUserService {
    User getUser(User user) throws Exception;
    public int setLoginInfo(User user) throws Exception;
}
