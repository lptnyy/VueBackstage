package com.ajax.ajaxweb.mapper.provider;

import java.util.Map;

public class FunctionProvider {
    String sql = "select t.id,t.name,t.type,t.url,t.parentId,t.stat,t.createTime from functions t where t.isDel = 0 ";
    String sqlCount = "select count(*) from functions t where t.isDel = 0 ";

    public String getFunction(Map map){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        String id = map.get("id").toString();
        stringBuffer.append(" and t.id =").append(id);
        return stringBuffer.toString();
    }

    public String getList(Map map){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        Map keys = (Map) map.get("keys");
        if (keys.get("id") != null) {
            stringBuffer.append(" and t.id=").append(keys.get("id"));
        }
        if (keys.get("name") != null && !keys.get("name").toString().equals("")){
            stringBuffer.append(" and t.name=").append(keys.get("name").toString());
        }
        if (keys.get("stat") != null && !keys.get("stat").toString().equals("")){
            stringBuffer.append(" and t.stat=").append(keys.get("stat").toString());
        }
        if (keys.get("type") != null) {
            stringBuffer.append(" and t.type=").append(keys.get("type"));
        }
        if (keys.get("parentId") != null) {
            stringBuffer.append(" and t.parentId=").append(keys.get("parentId"));
        }
        if (keys.get("order") != null) {
            stringBuffer.append(" order by ").append(keys.get("order").toString());
        }
        if (keys.get("limit") != null) {
            stringBuffer.append(" limit ").append(keys.get("limit").toString());
        }
        return stringBuffer.toString();
    }

    public String getSqlCount(Map map){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sqlCount);
        Map keys = (Map) map.get("keys");
        if (keys.get("id") != null) {
            stringBuffer.append(" and t.id=").append(keys.get("id"));
        }
        if (keys.get("name") != null && !keys.get("name").toString().equals("")){
            stringBuffer.append(" and t.name=").append(keys.get("name").toString());
        }
        if (keys.get("type") != null) {
            stringBuffer.append(" and t.type=").append(keys.get("type"));
        }
        if (keys.get("parentId") != null) {
            stringBuffer.append(" and t.parentId=").append(keys.get("parentId"));
        }
        if (keys.get("order") != null) {
            stringBuffer.append(" order by ").append(keys.get("order").toString());
        }
        if (keys.get("limit") != null) {
            stringBuffer.append(" limit ").append(keys.get("limit").toString());
        }
        return stringBuffer.toString();
    }
}
