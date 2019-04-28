package com.ajax.ajaxweb.util;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String uuidStr=uuid.toString();
        return uuidStr;
    }
}
