package com.ajax.ajaxweb.service;

import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.entity.RoleFunctions;
import com.ajax.ajaxweb.entity.vo.RoleVo;
import com.ajax.ajaxweb.mapper.RoleFunctionsMapper;
import com.ajax.ajaxweb.mapper.RoleMapper;
import com.ajax.ajaxweb.util.DateUtil;
import com.ajax.ajaxweb.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService{

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleFunctionsMapper roleFunctionsMapper;

    @Override
    public Role getRole(Role role) throws Exception {
        Map keys = MapUtil.objectToMap(role);
        return roleMapper.getRole(keys);
    }

    @Override
    public List<RoleVo> getRoleVos(Role role, int pageNo, int pageSize) throws Exception {
        Map map = MapUtil.objectToMap(role);
        map.put("limit", ((pageNo -1) * pageSize) +","+pageSize);
        List<RoleVo> roleVos = new ArrayList<>();
        roleMapper.getRoles(map).forEach(roleEn -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(roleEn, roleVo);
            roleVo.setCreateTime(DateUtil.getyyMMddHHmmss(roleEn.getCreateTime()));
            if (roleEn.getStat().equals(0)){
                roleVo.setStatString("正常");
            } else {
                roleVo.setStatString("已注销");
            }
            roleVos.add(roleVo);
        });
        return roleVos;
    }

    @Override
    public int getRoleVosCount(Role role) throws Exception {
        Map map = MapUtil.objectToMap(role);
        return roleMapper.getRolesCount(map);
    }

    @Override
    public int deleteRole(Integer roleId) throws Exception {
        roleFunctionsMapper.del(roleId);
        return roleMapper.deleteRoles(roleId);
    }

    @Override
    public int updateRoleStat(Integer roleId, Integer stat) throws Exception {
        return roleMapper.updateRolesStat(roleId, stat);
    }

    @Override
    public int addRole(Role role, String functons) {
        roleMapper.addRole(role);
        String[] funciton = functons.split(",");
        for(String fun:funciton) {
            RoleFunctions functions = new RoleFunctions();
            functions.setRoleId(role.getId());
            functions.setFunctionId(Integer.valueOf(fun));
            roleFunctionsMapper.add(functions);
        }
        return 0;
    }

    @Override
    public List<RoleFunctions> getRoleFunctions(Integer roleId) {
        return roleFunctionsMapper.getFindList(roleId);
    }

    @Override
    public int updateRole(Role role, String functons) {
        roleFunctionsMapper.del(role.getId());
        String[] funciton = functons.split(",");
        for(String fun:funciton) {
            RoleFunctions functions = new RoleFunctions();
            functions.setRoleId(role.getId());
            functions.setFunctionId(Integer.valueOf(fun));
            roleFunctionsMapper.add(functions);
        }
        return roleMapper.updateRole(role);
    }

    @Override
    public List<Role> getRoles(Role role) throws Exception{
        Map map = MapUtil.objectToMap(role);
        return roleMapper.getRoles(map);
    }
}
