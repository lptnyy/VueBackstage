package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.Function;
import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.entity.User;
import com.ajax.ajaxweb.entity.UserRole;
import com.ajax.ajaxweb.service.IFunctionService;
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
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private IFunctionService functionService;

    /**
     * 查询左侧栏
     * @param userId
     * @return
     */
    @RequestMapping(value = "/leftMenu", method = RequestMethod.GET)
    public String leftMenu(
            @RequestParam(value = "userId", defaultValue = "") String userId) {
        JsonVo<List<Function>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            if (!StringUtils.isNotNull(userId)){
                jsonVo.setResult(false);
                jsonVo.setMsg("用户id不能为空");
                return jsonVo.toString();
            }
            jsonVo.setObj(functionService.getLeftUserFunctions(Integer.valueOf(userId)));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }
}
