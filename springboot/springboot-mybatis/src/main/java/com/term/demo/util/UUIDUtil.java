package com.term.demo.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtil {
    public static String getUUID() {
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        return uid;
    }
}
