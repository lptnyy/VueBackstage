package com.ajax.ajaxweb.entity.vo;

import java.util.List;

public class FunctionVo {
    Integer id;
    String name;
    Integer type;
    String typeName;
    String url;
    Integer parentId;
    String parentName;
    Integer stat;
    String statString;
    String createTime;
    List<FunctionVo> functionVoList;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStat() {
        return stat;
    }

    public String getStatString() {
        return statString;
    }

    public void setStatString(String statString) {
        this.statString = statString;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url;}

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public List<FunctionVo> getFunctionVoList() {
        return functionVoList;
    }

    public void setFunctionVoList(List<FunctionVo> functionVoList) {
        this.functionVoList = functionVoList;
    }
}
