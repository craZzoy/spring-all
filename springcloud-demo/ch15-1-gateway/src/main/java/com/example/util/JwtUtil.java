package com.example.util;

import com.example.filter.PermissionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: jwt工具类
 * @Author : 郑玮泽
 * @Date : 14:52 2021/2/5
 */
public class JwtUtil {

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_AUTH = "Authorization";

    /**
     * 根据用户名生成jwt token
     * @param user
     * @return
     */
    public static String generateToken(String user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", new Random().nextInt());
        map.put("user", user);
        String jwt = Jwts.builder()
                .setSubject("user info")
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return TOKEN_PREFIX + " " + jwt;
    }

    /**
     * token 校验
     * @param token
     * @return
     */
    public static Map<String, String> validateToken(String token) {
        if (token != null){
            Map<String, String> map = new HashMap<>();
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String id = String.valueOf(body.get("id"));
            String user = (String) body.get("user");
            if(StringUtils.isEmpty(user)){
                throw new PermissionException("user is error, please check");
            }
            map.put("id", id);
            map.put("user", user);
            return map;
        }
        throw new PermissionException("user is error, please check");
    }

    public static void main(String[] args) {
        String token = generateToken("admin");
        System.out.println(token);
        System.out.println(validateToken(token));
    }
}
