package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.entity.UserRole;
import com.ajax.ajaxweb.service.RoleService;
import com.ajax.ajaxweb.service.UserService;
import com.ajax.ajaxweb.util.JsonVo;
import com.ajax.ajaxweb.util.StringUtils;
import com.ajax.ajaxweb.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "userName", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password,
            @RequestParam(value = "jsonp", defaultValue = "") String jsonp) {
        JsonVo<User> jsonVo = new JsonVo<>();
        jsonVo.setCallback(jsonp);

        if (!StringUtils.isNotNull(username)){
            jsonVo.setResult(false);
            jsonVo.setMsg("用户名不能为空");
            return jsonVo.toString();
        }

        if (!StringUtils.isNotNull(password)) {
            jsonVo.setResult(false);
            jsonVo.setMsg("密码不能为空");
            return jsonVo.toString();
        }

        //根据权限，指定返回数据
        User userVo = new User();
        userVo.setUserName(username);
        try {
            User user = userService.getUser(userVo);
            if (user == null) {
                jsonVo.setResult(false);
                jsonVo.setMsg("此用户不存在");
                return jsonVo.toString();
            }
            if (!user.getUserPass().equals(password)){
                jsonVo.setResult(false);
                jsonVo.setMsg("密码错误");
                return jsonVo.toString();
            }
            Role roleVo = new Role();
            roleVo.setId(user.getRoleId());
            List<UserRole> userRoles = userService.getUserRoles(user.getId());
            if (userRoles.size() < 0) {
                jsonVo.setResult(false);
                jsonVo.setMsg("用户没有设置权限/权限已被注销");
                return jsonVo.toString();
            }
            user.setUserName("");
            user.setUserPass("");
            user.setToken(UUIDUtil.getUUID());
            userService.setLoginInfo(user);
            jsonVo.setObj(user);
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }
}
