package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.entity.RoleFunctions;
import com.ajax.ajaxweb.entity.vo.RoleVo;
import com.ajax.ajaxweb.service.IRoleService;
import com.ajax.ajaxweb.util.JsonVo;
import com.ajax.ajaxweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    IRoleService iRoleService;

    @RequestMapping(path = "/getRoles")
    public String getRoles(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize){
        JsonVo<List<RoleVo>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            Role role = new Role();
            role.setRoleName(name);
            jsonVo.setObj(iRoleService.getRoleVos(role, pageNo,pageSize));
            jsonVo.setDataCount(iRoleService.getRoleVosCount(role));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 查询角色列表
     * @return
     */
    @RequestMapping(path = "/getUserRoles")
    public String getRoles(){
        JsonVo<List<Role>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            Role role = new Role();
            role.setStat(0);
            jsonVo.setObj(iRoleService.getRoles(role));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * delete role
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteRoles")
    public String deleteRoles(
            @RequestParam(value = "id", defaultValue = "") String id){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(id)){
            jsonVo.setResult(false);
            jsonVo.setMsg("id is null");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(id)){
            jsonVo.setResult(false);
            jsonVo.setMsg("id is not number");
            return jsonVo.toString();
        }
        try{
            jsonVo.setObj(iRoleService.deleteRole(Integer.valueOf(id)));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * delete role
     * @param id
     * @return
     */
    @RequestMapping(path = "/updateRolesStat")
    public String updateRolesStat(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "stat", defaultValue = "") String stat){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(id)){
            jsonVo.setResult(false);
            jsonVo.setMsg("id is null");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(id)){
            jsonVo.setResult(false);
            jsonVo.setMsg("id is not number");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNull(stat)){
            jsonVo.setResult(false);
            jsonVo.setMsg("stat is null");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(stat)){
            jsonVo.setResult(false);
            jsonVo.setMsg("stat is not number");
            return jsonVo.toString();
        }
        try{
            jsonVo.setObj(iRoleService.updateRoleStat(Integer.valueOf(id), Integer.valueOf(stat)));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * add role
     * @param roleName
     * @param functions
     * @return
     */
    @RequestMapping(path = "/addRole")
    public String addRole(
            @RequestParam(value = "roleName", defaultValue = "") String roleName,
            @RequestParam(value = "functions", defaultValue = "") String functions){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(roleName)){
            jsonVo.setResult(false);
            jsonVo.setMsg("角色名称不能为空");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNull(functions)){
            jsonVo.setResult(false);
            jsonVo.setMsg("没有选择权限内容");
            return jsonVo.toString();
        }
        try{
            Role role = new Role();
            role.setRoleName(roleName);
            if (iRoleService.getRole(role) != null) {
                jsonVo.setResult(false);
                jsonVo.setMsg("该角色名已经存在");
                return jsonVo.toString();
            }
            jsonVo.setObj(iRoleService.addRole(role, functions));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * add role
     * @roleId
     * @return
     */
    @RequestMapping(path = "/getRoleFunctions")
    public String getRoleFunctions(
            @RequestParam(value = "roleId", defaultValue = "") String roleId){
        JsonVo<List<RoleFunctions>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(roleId)){
            jsonVo.setResult(false);
            jsonVo.setMsg("roldId  不能为空");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(roleId))  {
            jsonVo.setResult(false);
            jsonVo.setMsg("roldId 只能是数字");
            return jsonVo.toString();
        }
        try{
            jsonVo.setObj(iRoleService.getRoleFunctions(Integer.valueOf(roleId)));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * add role
     * @param roleName
     * @param functions
     * @return
     */
    @RequestMapping(path = "/updateRole")
    public String updateRole(
            @RequestParam(value = "roleId", defaultValue = "") String roleId,
            @RequestParam(value = "roleName", defaultValue = "") String roleName,
            @RequestParam(value = "functions", defaultValue = "") String functions){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(roleName)){
            jsonVo.setResult(false);
            jsonVo.setMsg("角色名称不能为空");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNull(functions)){
            jsonVo.setResult(false);
            jsonVo.setMsg("没有选择权限内容");
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNull(roleId)){
            jsonVo.setResult(false);
            jsonVo.setMsg("角色id不能为空");
            return jsonVo.toString();
        }
        try{
            Role role = new Role();
            role.setRoleName(roleName);
            role = iRoleService.getRole(role);
            if (role != null && !role.getId().equals(Integer.valueOf(roleId))) {
                jsonVo.setResult(false);
                jsonVo.setMsg("该角色名已经存在");
                return jsonVo.toString();
            }
            role = new Role();
            role.setRoleName(roleName);
            role.setId(Integer.valueOf(roleId));
            jsonVo.setObj(iRoleService.updateRole(role, functions));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }
}
