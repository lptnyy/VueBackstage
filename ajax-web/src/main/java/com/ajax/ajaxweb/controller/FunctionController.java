package com.ajax.ajaxweb.controller;
import com.ajax.ajaxweb.entity.Function;
import com.ajax.ajaxweb.entity.vo.FunctionVo;
import com.ajax.ajaxweb.service.IFunctionService;
import com.ajax.ajaxweb.util.JsonVo;
import java.util.List;

import com.ajax.ajaxweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class FunctionController {

    @Autowired
    IFunctionService functionService;
    @RequestMapping(path = "/getFunctions")
    public String getFunctions(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize){
        JsonVo<List<FunctionVo>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            Function function = new Function();
            function.setName(name);
            jsonVo.setObj(functionService.getList(function, pageNo,pageSize));
            jsonVo.setDataCount(functionService.getListCount(function));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 查询所有父栏目
     * @return
     */
    @RequestMapping(path = "/getParentFunctions")
    public String getParentFunctions(){
        JsonVo<List<FunctionVo>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            Function function = new Function();
            function.setType(0);
            jsonVo.setObj(functionService.getList(function));
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 添加一个栏目
     * @param name
     * @param url
     * @param type
     * @param parentId
     * @return
     */
    @RequestMapping(path = "/addFunctions")
    public String addFunction(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "url", defaultValue = "") String url,
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "parentId", defaultValue = "") String parentId){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (name.equals("")){
            jsonVo.setMsg("名称不能为空");
            jsonVo.setResult(false);
            return jsonVo.toString();
        } else if (type.equals("")){
            jsonVo.setMsg("没有选择栏目类型");
            jsonVo.setResult(false);
            return jsonVo.toString();
        } else if (type.equals("1")){
            if (url.equals("")){
                jsonVo.setMsg("url地址不能为空");
                jsonVo.setResult(false);
                return jsonVo.toString();
            }
            if (parentId.equals("")){
                jsonVo.setMsg("没有选择父栏目");
                jsonVo.setResult(false);
                return jsonVo.toString();
            }
        }
        try{
            Function function = new Function();
            function.setName(name);
            function.setType(Integer.valueOf(type));
            if (parentId.equals("")) parentId = "0";
            function.setParentId(Integer.valueOf(parentId));
            function.setUrl(url);
            jsonVo.setObj(functionService.add(function));
            jsonVo.setResult(true);
        } catch (Exception e){
            e.printStackTrace();
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 删除一个栏目
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteFunctions")
    public String deleteFunction(
            @RequestParam(value = "id", defaultValue = "") String id){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(id)){
            jsonVo.setMsg("id不能为空");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(id)){
            jsonVo.setMsg("id只能是数字");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        try{
            jsonVo.setObj(functionService.delete(Integer.valueOf(id)));
            jsonVo.setResult(true);
        } catch (Exception e){
            e.printStackTrace();
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }

    /**
     * 修改状态
     * @param id
     * @param stat
     * @return
     */
    @RequestMapping(path = "/updateFunctionStat")
    public String updateStat(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "stat", defaultValue = "") String stat){
        JsonVo<Integer> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        if (!StringUtils.isNotNull(id)){
            jsonVo.setMsg("id不能为空");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(id)){
            jsonVo.setMsg("id只能是数字");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNull(stat)){
            jsonVo.setMsg("stat不能为空");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        if (!StringUtils.isNotNumber(stat)){
            jsonVo.setMsg("stat只能是数字");
            jsonVo.setResult(false);
            return jsonVo.toString();
        }
        try{
            jsonVo.setObj(functionService.updateStat(Integer.valueOf(id),Integer.valueOf(stat)));
            jsonVo.setResult(true);
        } catch (Exception e){
            e.printStackTrace();
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }


    /**
     * get Authorizations
     * @return
     */
    @RequestMapping(path = "/getAuthorizations")
    public String getAuthorizations(){
        JsonVo<List<FunctionVo>> jsonVo = new JsonVo<>();
        jsonVo.setCallback("true");
        try{
            jsonVo.setObj(functionService.getAuthorizations());
            jsonVo.setResult(true);
        } catch (Exception e) {
            jsonVo.setResult(false);
            jsonVo.setMsg(e.getMessage());
        }
        return jsonVo.toString();
    }
}
