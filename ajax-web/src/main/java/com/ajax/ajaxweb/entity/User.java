package com.ajax.ajaxweb.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {
    Integer id;
    String userName;
    String userPass;
    Integer roleId;
    String token;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    Date createTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    Date lastLloginTime;
    Integer loginNum;
    Integer stat;
    String statString;

    public Integer getId() {
        return id;
    }

    public Date getLastLloginTime() {
        return lastLloginTime;
    }

    public String getStatString() {
        return statString;
    }

    public void setStatString(String statString) {
        this.statString = statString;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public void setLastLloginTime(Date lastLloginTime) {
        this.lastLloginTime = lastLloginTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
