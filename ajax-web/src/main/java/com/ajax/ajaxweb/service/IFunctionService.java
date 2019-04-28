package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.Function;
import com.ajax.ajaxweb.entity.vo.FunctionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFunctionService {
    List<FunctionVo> getList(Function function) throws Exception;
    List<FunctionVo> getList(Function function, int pageNo, int pageSize) throws Exception;
    int getListCount(Function function) throws Exception;
    Function getFunction(Integer id) throws Exception;
    int add(Function function) throws Exception;
    int delete(Integer id) throws Exception;
    int updateStat(Integer id, Integer stat) throws Exception;
    List<FunctionVo> getAuthorizations() throws Exception;
}
