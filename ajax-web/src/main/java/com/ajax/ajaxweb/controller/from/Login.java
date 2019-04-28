package com.ajax.ajaxweb.controller.from;
import javax.validation.constraints.NotEmpty;
public class Login {
    @NotEmpty(message = "登录名不能为空")
    String username;
    @NotEmpty(message = "登录密码不能为空")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
