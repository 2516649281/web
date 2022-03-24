package com.chunfeng;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;


@SpringBootTest
public class tokenTest {

    /**
     * 时间
     */
    private final long time = 1000 * 60 * 60 * 24;
    private final String key = "chunfeng";

    /**
     * 生成token
     */
    @Test
    void getTest() {
        JwtBuilder jwtBuilder = Jwts.builder();
        //生成token
        String jwt = jwtBuilder
                //头部
                .setHeaderParam("type", "jwt")
                .setHeaderParam("alg", "HS256")
                //载荷
                .claim("userName", "root")
                .claim("userId", 1)
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //密钥
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析token
     */
    @Test
    void setTest() {
        String token = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VyTmFtZSI6InJvb3QiLCJ1c2VySWQiOjEsImV4cCI6MTY0ODA4NjExOSwianRpIjoiYzY2Mzc5MTAtNDljMC00NjMyLWE4MDQtNDFhNmZiYWY4OGQ5In0.0YA6teFXll57-HEWzpHzNEFbjOCerE3TQb66xet9jec";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(key).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        System.out.println(body.get("userName"));
        System.out.println(body.get("userId"));
    }

    @Test
    void token() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImFjY291bnRJZCI6MTQsImFjY291bnROYW1lIjoiYWRtaW4iLCJhY2NvdW50UGFzc3dvcmQiOiIxMjM0NTYiLCJhY2NvdW50RGVsZXRlZCI6MCwiYWNjb3VudENyZWF0ZVRpbWUiOjE2NDc2MDUwODUwMDAsImFjY291bnRVcGRhdGVUaW1lIjoxNjQ3NjEzMTY5MDAwLCJ0b2tlbiI6bnVsbCwidXNlciI6eyJ1c2VySWQiOjE1LCJ1c2VyTmFtZSI6IuWwj-iKsSIsInVzZXJBZ2UiOjE5LCJ1c2VyU2V4Ijoi5aWzIiwidXNlckFkZHJlc3MiOiLkuIrmtbciLCJ1c2VyUGhvbmUiOiIxNTk4NzQ2MzI1OCIsInVzZXJEdXR5IjoyLCJhY2NvdW50SWQiOjE0fX0sInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NDgwMDYxNTgsImp0aSI6IjNjZTgwN2MzLTMwNWQtNDgxOC1hMjA1LTNiZWNiY2Q4Y2ZiYSJ9.dqd_fKjDXHntDRKd-8ZhOOxeo4wjgR2H-f4QXNoFDxM";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey("chunfeng@2516649281$").parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        System.out.println(body.get("user"));
    }
}
