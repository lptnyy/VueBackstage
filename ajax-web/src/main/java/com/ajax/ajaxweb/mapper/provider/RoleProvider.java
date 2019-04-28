package com.ajax.ajaxweb.mapper.provider;

import java.util.Map;

public class RoleProvider {
    String sql = "select t.id,t.roleName,t.stat,t.createTime from role t where 1 = 1 ";
    String sqlCount = "select count(*) from role t where 1 = 1 ";

    /**
     * 查询单个用户信息  此方法代替xml 配置方式
     * @param map
     * @return
     */
    public String getRole(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        Map<Object,Object> keys = (Map<Object, Object>) map.get("keys");
        if (keys.get("roleName") != null && !keys.get("roleName").toString().equals("")){
            stringBuffer.append(" and t.roleName ='").append(keys.get("roleName")).append("' ");
        }
        if (keys.get("id") != null){
            stringBuffer.append(" and t.id = ").append(keys.get("id").toString());
        }
        if (keys.get("stat") != null) {
            stringBuffer.append(" and t.stat = ").append(keys.get("stat").toString());
        }
        if (keys.get("order") != null) {
            stringBuffer.append(" order by ").append(keys.get("order").toString());
        }
        if (keys.get("limit") != null) {
            stringBuffer.append(" limit ").append(keys.get("limit").toString());
        }
        return stringBuffer.toString();
    }

    /**
     * 查询数量
     * @param map
     * @return
     */
    public String getRoleCount(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sqlCount);
        Map<Object,Object> keys = (Map<Object, Object>) map.get("keys");
        if (keys.get("roleName") != null && !keys.get("roleName").toString().equals("")){
            stringBuffer.append(" and t.roleName ='").append(keys.get("roleName")).append("' ");
        }
        if (keys.get("id") != null){
            stringBuffer.append(" and t.id = ").append(keys.get("id").toString());
        }
        if (keys.get("stat") != null) {
            stringBuffer.append(" and t.stat = ").append(keys.get("stat").toString());
        }
        return stringBuffer.toString();
    }
}
