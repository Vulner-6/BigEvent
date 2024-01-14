package com.my.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest
{
    /**
     * 生成 jwt token
     */
    @Test
    public void testGen()
    {
        //准备信息
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");

        //生成 jwt
        String token= JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))   // 12小时后过期
                .sign(Algorithm.HMAC256("BigEvent"));  // 指定算法，配置密钥

        // 打印密文
        System.out.println(token);
    }

    /**
     * 测试解析 jwt token
     */
    /*
    @Test
    public void testParse()
    {
        // 模拟前端传送到后端的jwt token
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDQyMDM2OTN9.pJbEHpE7FwN_-rI-eY3TXpHRX-lootw8R7IeJVKBFyc";

        // 根据后端生成 token 的算法与密钥，制作一个校验器
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("BigEvent")).build();

        // 验证 token ，生成一个解析后的 jwt 对象。
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        // 获取解析后的 jwt 对象中的信息
        Map<String, Claim> claims=decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
    */

}
