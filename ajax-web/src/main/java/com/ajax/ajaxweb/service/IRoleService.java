package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.Role;
import com.ajax.ajaxweb.entity.RoleFunctions;
import com.ajax.ajaxweb.entity.vo.RoleVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleService {
    Role getRole(Role role) throws Exception;
    List<RoleVo> getRoleVos(Role role, int pageNo,int pageSize) throws Exception;
    int getRoleVosCount(Role role) throws Exception;
    @Transactional
    int deleteRole(Integer roleId) throws Exception;
    int updateRoleStat(Integer roleId, Integer stat) throws Exception;
    @Transactional
    int addRole(Role role, String functons);
    List<RoleFunctions> getRoleFunctions(Integer roleId);
    @Transactional
    int updateRole(Role role, String functons);
    List<Role> getRoles(Role role) throws Exception;
}
