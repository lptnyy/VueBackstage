package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.service.IUserService;
import com.ajax.ajaxweb.util.JsonVo;
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
    public String getRoles(
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
    public String getRoles(
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
}
