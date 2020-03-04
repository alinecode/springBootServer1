package com.hello.store.test.util;

import java.util.Date;

import com.hello.store.test.dto.UserAccountDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    // 主题
    public static final String SUBJECT = "georgec";

    // 秘钥
    public static final String SECRETKEY = "george666";

    // 过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;  //过期时间，毫秒，一周

    // 生成 JWT
    public static String geneJsonWebToken(UserAccountDto user) {

        if (user == null ||
                user.getId() == null ||
                user.getAccount() == null  ) {

            return null;
        }
        String token = Jwts.builder()
                .setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("account", user.getAccount())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();

        return token;
    }


    // 校验 JWT
    public static Claims checkJWT(String token) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRETKEY).
                    parseClaimsJws(token).getBody();
            return claims;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}