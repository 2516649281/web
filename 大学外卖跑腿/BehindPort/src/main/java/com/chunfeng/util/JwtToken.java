package com.chunfeng.util;

import com.chunfeng.dao.entity.Account;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类
 */
public class JwtToken {

    /**
     * 过期时间
     * 设置15min过期
     */
    private static final long time = 1000 * 60 * 60 * 2;

    /**
     * 密钥
     */
    private static final String signature = "chunfeng@2516649281$";

    /**
     * 创建token的方法
     *
     * @param account 请求的账号对象
     * @return token
     */
    public static String createToken(Account account) {
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("user", account)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
    }

    /**
     * 校验token，布尔类型
     *
     * @param token token
     * @return boolean
     */
    public static boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
            if (claimsJws.getBody().get("user") != null) {//判断token载荷是否为空
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}