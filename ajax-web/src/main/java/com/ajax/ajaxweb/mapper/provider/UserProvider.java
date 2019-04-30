package com.ajax.ajaxweb.mapper.provider;
import java.util.Map;

public class UserProvider {
    String sql = "select case t.stat when 0 then '正常' else '已注销' end as statString,t.id,t.userName,t.roleId,t.userPass,t.createTime,t.loginNum,t.lastLloginTime,t.stat from user t where 1 = 1 ";
    String sqlCount = "select count(*) from user t where 1 = 1";

    /**
     * 查询单个用户信息  此方法代替xml 配置方式
     * @param map
     * @return
     */
    public String getUser(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        Map<String,String> keys = (Map<String, String>) map.get("keys");
        if (keys.get("userName") != null && !keys.get("userName").equals("")){
            stringBuffer.append(" and t.userName ='").append(keys.get("userName")).append("' ");
        }
        if (keys.get("userPass") != null && !keys.get("userPass").equals("")){
            stringBuffer.append(" and t.userPass ='").append(keys.get("userPass")).append("' ");
        }
        if (keys.get("id") != null && !keys.get("id").equals("")){
            stringBuffer.append(" and t.id = ").append(keys.get("id"));
        }
        return stringBuffer.toString();
    }

    /**
     * 查询单个用户信息  此方法代替xml 配置方式
     * @param map
     * @return
     */
    public String getUsers(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        Map<String,String> keys = (Map<String, String>) map.get("keys");
        if (keys.get("userName") != null && !keys.get("userName").equals("")){
            stringBuffer.append(" and t.userName ='").append(keys.get("userName")).append("' ");
        }
        if (keys.get("limit") != null) {
            stringBuffer.append(" limit ").append(keys.get("limit").toString());
        }
        return stringBuffer.toString();
    }

    /**
     * 查询单个用户信息  此方法代替xml 配置方式
     * @param map
     * @return
     */
    public String getUsersCount(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sqlCount);
        Map<String,String> keys = (Map<String, String>) map.get("keys");
        if (keys.get("userName") != null && !keys.get("userName").equals("")){
            stringBuffer.append(" and t.userName ='").append(keys.get("userName")).append("' ");
        }
        return stringBuffer.toString();
    }
}
