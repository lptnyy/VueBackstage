package com.ajax.ajaxweb.service;
import com.ajax.ajaxweb.entity.Function;
import com.ajax.ajaxweb.entity.vo.FunctionVo;
import com.ajax.ajaxweb.mapper.FunctionMapper;
import com.ajax.ajaxweb.util.DateUtil;
import com.ajax.ajaxweb.util.MapUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

@Service
public class FunctionService implements IFunctionService{

    @Resource
    FunctionMapper functionMapper;

    @Override
    public List<FunctionVo> getList(Function function) throws Exception {
        Map map = MapUtil.objectToMap(function);
        List<FunctionVo> functionVos = new ArrayList<>();
        functionMapper.getList(map).forEach(functionEn -> {
            FunctionVo functionVo = new FunctionVo();
            BeanUtils.copyProperties(functionEn,functionVo);
            functionVo.setCreateTime(DateUtil.getyyMMddHHmmss(functionEn.getCreateTime()));
            functionVos.add(functionVo);
        });
        return functionVos;
    }

    @Override
    public List<FunctionVo> getList(Function function, int pageNo, int pageSize) throws Exception {
        Map map = MapUtil.objectToMap(function);
        map.put("limit", ((pageNo -1) * pageSize) +","+pageSize);
        List<FunctionVo> functionVos = new ArrayList<>();
        functionMapper.getList(map).forEach(functionEn -> {
            FunctionVo functionVo = new FunctionVo();
            BeanUtils.copyProperties(functionEn,functionVo);
            functionVo.setCreateTime(DateUtil.getyyMMddHHmmss(functionEn.getCreateTime()));
            if (functionVo.getType().equals(0)) {
                functionVo.setTypeName("模块");
            } else if (functionVo.getType().equals(1)){
                functionVo.setTypeName("页面");
            }
            if (functionEn.getStat().equals(0)){
                functionVo.setStatString("正常");
            } else {
                functionVo.setStatString("已注销");
            }
            if (functionVo.getParentId().equals(0)){
                functionVo.setParentName("");
            } else {
                try {
                    functionVo.setParentName(getFunction(functionEn.getParentId()).getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            functionVos.add(functionVo);
        });
        return functionVos;
    }

    @Override
    public int getListCount(Function function) throws Exception {
        Map map = MapUtil.objectToMap(function);
        return functionMapper.getCount(map);
    }

    @Override
    @Cacheable(value = "function", key = "#id")
    public Function getFunction(Integer id) throws Exception {
        return functionMapper.getFunction(id);
    }

    @Override
    public int add(Function function) throws Exception {
        return functionMapper.add(function);
    }

    @Override
    public int delete(Integer id) throws Exception {
        return functionMapper.delete(id);
    }

    @Override
    public int updateStat(Integer id, Integer stat) throws Exception {
        return functionMapper.updateStat(id,stat);
    }

    @Override
    public List<FunctionVo> getAuthorizations() throws Exception {
        Function function = new Function();
        function.setType(0);
        function.setStat(0);
        List<FunctionVo> functionVos = new ArrayList<>();
        functionMapper.getList(MapUtil.objectToMap(function)).forEach(functionEn -> {
            FunctionVo functionVo = new FunctionVo();
            BeanUtils.copyProperties(functionEn, functionVo);
            Function conKeys = new Function();
            conKeys.setParentId(functionEn.getId());
            conKeys.setStat(0);
            try {
                List<FunctionVo> conFuntions = new ArrayList<>();
                functionMapper.getList(MapUtil.objectToMap(conKeys)).forEach(functionEn2 -> {
                    FunctionVo functionVo2 = new FunctionVo();
                    BeanUtils.copyProperties(functionEn2, functionVo2);
                    conFuntions.add(functionVo2);
                });
                functionVo.setFunctionVoList(conFuntions);
            } catch (Exception e) {

            }
            functionVos.add(functionVo);
        });
        return functionVos;
    }

    @Override
    public List<Function> getLeftUserFunctions(Integer userId) throws Exception {
        List<Function> functions = functionMapper.getLeftUserFunctions(userId);
        List<Function> newFunctions = new ArrayList<>();
        Integer paerId = 0;
        Function newFunction = null;
        for(Function function: functions) {
            if (!paerId.equals(function.getParentId())){
                newFunction = new Function();
                newFunction.setFunctions(new ArrayList<>());
                newFunction.setId(function.getParentId());
                newFunction.setName(function.getParentName());
                newFunction.getFunctions().add(function);
                newFunction.setUrl(function.getParentUrl());
                newFunctions.add(newFunction);
                paerId = function.getParentId();
            } else {
                newFunction.getFunctions().add(function);
            }
        }
        return newFunctions;
    }
}
