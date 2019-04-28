package com.ajax.ajaxweb.util;

public class StringUtils {

    /**
     * 验证是否为空
     * @param arg
     * @return
     */
    public static boolean isNotNull(String arg){
        if (arg == null || arg.equals("")){
            return false;
        }
        return true;
    }

    /**
     * 验证是否为数字
     * @param arg
     * @return
     */
    public static boolean isNotNumber(String arg){
        if (!isNotNull(arg)){
            return false;
        }
        try{
            Long.valueOf(arg);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
