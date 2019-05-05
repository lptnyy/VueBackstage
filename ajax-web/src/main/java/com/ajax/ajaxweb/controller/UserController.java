package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.entity.UserRole;
import com.ajax.ajaxweb.service.IUserService;
import com.ajax.ajaxweb.util.JsonVo;
import com.ajax.ajaxweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 获取用户列表
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "/getUsers")
    public String getUsers(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize){
        JsonVo<List<User>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            User user = new User();
            user.setUserName(name);
            jsonVo.setObj(userService.getUsers(user,pageNo, pageSize));
            jsonVo.setDataCount(userService.getUsersCount(user));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 修改用户状态
     * @param id
     * @param stat
     * @return
     */
    @RequestMapping(path = "/updateStat")
    public String updateStat(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "stat", defaultValue = "") int stat){
        JsonVo<List<User>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            User user = new User();
            user.setId(Integer.valueOf(id));
            user.setStat(Integer.valueOf(stat));
            userService.updateUserStat(user);
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteUser")
    public String deleteUser(
            @RequestParam(value = "id", defaultValue = "") String id){
        JsonVo<List<User>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            userService.deleteUser(Integer.valueOf(id));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping(path = "/addUser")
    public String addUser(
            @RequestParam(value = "userName", defaultValue = "") String userName,
            @RequestParam(value = "userPass", defaultValue = "") String userPass,
            @RequestParam(value = "reuserPass", defaultValue = "") String reuserPass,
            @RequestParam(value = "roles", defaultValue = "") String roles){
        JsonVo<List<User>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            if(!StringUtils.isNotNull(userName)){
                jsonVo.setResult(false);
                jsonVo.setMsg("用户名不能为空");
                return jsonVo.toString();
            }
            if(!StringUtils.isNotNull(userPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("用户密码不能为空");
                return jsonVo.toString();
            }
            if(!StringUtils.isNotNull(reuserPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("重复密码不能为空");
                return jsonVo.toString();
            }
            if(!userPass.equals(reuserPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("两次密码输入不同");
                return jsonVo.toString();
            }
            if (!StringUtils.isNotNull(roles)) {
                jsonVo.setResult(false);
                jsonVo.setMsg("没有选择角色");
                return jsonVo.toString();
            }
            User user = new User();
            user.setUserName(userName);
            User oldUser = userService.getUser(user);
            if (oldUser != null) {
                jsonVo.setResult(false);
                jsonVo.setMsg("此用户已经存在");
                return jsonVo.toString();
            }
            user.setUserPass(userPass);
            userService.addUser(user,roles);
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping(path = "/updateUser")
    public String updateUser(
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "userPass", defaultValue = "") String userPass,
            @RequestParam(value = "reuserPass", defaultValue = "") String reuserPass,
            @RequestParam(value = "roles", defaultValue = "") String roles){
        JsonVo<List<User>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            if(!StringUtils.isNotNull(userId)){
                jsonVo.setResult(false);
                jsonVo.setMsg("用户ID密码不能为空");
                return jsonVo.toString();
            }
            if(!StringUtils.isNotNull(userPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("用户密码不能为空");
                return jsonVo.toString();
            }
            if(!StringUtils.isNotNull(reuserPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("重复密码不能为空");
                return jsonVo.toString();
            }
            if(!userPass.equals(reuserPass)){
                jsonVo.setResult(false);
                jsonVo.setMsg("两次密码输入不同");
                return jsonVo.toString();
            }
            if (!StringUtils.isNotNull(roles)) {
                jsonVo.setResult(false);
                jsonVo.setMsg("没有选择角色");
                return jsonVo.toString();
            }
            User user = new User();
            user.setId(Integer.valueOf(userId));
            user.setUserPass(userPass);
            userService.updateUser(user,roles);
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 获取用户角色
     * @return
     */
    @RequestMapping(path = "/getOptionUserRoles")
    public String getOptionUserRoles(
            @RequestParam(value = "userId", defaultValue = "") String userId){
        JsonVo<List<UserRole>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            if(!StringUtils.isNotNull(userId)) {
                jsonVo.setResult(false);
                jsonVo.setMsg("用户Id不能为空");
                return jsonVo.toString();
            }
            jsonVo.setObj(userService.getUserRoles(Integer.valueOf(userId)));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }
}
