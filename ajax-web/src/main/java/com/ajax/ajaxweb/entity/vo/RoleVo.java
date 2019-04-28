package com.ajax.ajaxweb.entity.vo;

public class RoleVo {
    Integer id;
    String roleName;
    Integer stat;
    String statString;
    String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getStatString() {
        return statString;
    }

    public void setStatString(String statString) {
        this.statString = statString;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
