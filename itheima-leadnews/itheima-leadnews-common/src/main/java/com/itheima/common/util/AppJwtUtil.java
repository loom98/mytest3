package com.itheima.common.util;


import com.itheima.common.constants.SystemConstants;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class AppJwtUtil {

    // TOKEN的有效期一天（S）
    private static final int TOKEN_TIME_OUT = 3600;
    // 加密KEY
    private static final String TOKEN_ENCRY_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    // 最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    // 生产ID

    /**
     * 登录的用户的ID的值
     * @param id
     * @return
     */
    public static String createToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        //自己封装的数据用户的ID
        claimMaps.put("id", id);
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                //唯一标识
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTime))  //签发时间
                .setSubject("system")  //说明
                .setIssuer("heima") //签发者信息
                .setAudience("app")  //接收用户
                .compressWith(CompressionCodecs.GZIP)  //数据压缩方式
                .signWith(SignatureAlgorithm.HS512, generalKey()) //加密方式
                //过期一个小时
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000))  //过期时间戳
                .addClaims(claimMaps) //cla信息
                .compact();
    }

    /**
     * 获取token中的claims信息 对称加密 JWT 还支持非对称的
     *
     * @param token
     * @return
     */
    private static Jws<Claims> getJws(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token);
    }

    /**
     * 获取payload body信息
     *
     * @param token
     * @return
     */
    public static Claims getClaimsBody(String token) {
        try {
            return getJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    /**
     * 获取hearder body信息
     *
     * @param token
     * @return
     */
    public static JwsHeader getHeaderBody(String token) {
        return getJws(token).getHeader();
    }

    /**
     * 是否过期  数字 魔法数字
     *
     * @param token
     * @return 1 有效  0 无效  2 已过期
     */
    public static Integer verifyToken(String token) {

        try {
            Claims claims = AppJwtUtil.getClaimsBody(token);
            if (claims == null) {
                return SystemConstants.JWT_FAIL;
            }
            return SystemConstants.JWT_OK;
        } catch (ExpiredJwtException ex) {
            return SystemConstants.JWT_EXPIRE;
        } catch (Exception e) {
            return SystemConstants.JWT_FAIL;
        }
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(TOKEN_ENCRY_KEY.getBytes());
        //对称加密
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static void main(String[] args) {
        //生成令牌
        String token = AppJwtUtil.createToken(1102L);

        System.out.println(token);

        //解析出令牌信息
        Claims claims = AppJwtUtil.getClaimsBody(token);

        String id = claims.getId();
        Object id1 = claims.get("id");


        //校验令牌
        Integer integer = AppJwtUtil.verifyToken(token);

        System.out.println(integer);
        System.out.println(claims);

    }
}