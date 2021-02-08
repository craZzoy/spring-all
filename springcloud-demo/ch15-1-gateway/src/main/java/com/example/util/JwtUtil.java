package com.example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: jwt工具类
 * @Author : 郑玮泽
 * @Date : 14:52 2021/2/5
 */
public class JwtUtil {

    public static final String HEADER_AUTH = "";

    public static String generateToken(String user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", new Random().nextInt());
        map.put("user", user);
        Jwts.b
    }

    public static Map<String, String> validateToken(String token) {
    }
}
