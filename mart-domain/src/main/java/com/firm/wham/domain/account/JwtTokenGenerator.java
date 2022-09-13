package com.firm.wham.domain.account;

import cn.hutool.core.convert.Convert;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

/**
 * @author ricardo zhou
 */
@Slf4j
@UtilityClass
public class JwtTokenGenerator {

    /**
     * JWT存储的请求头
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * JWT负载中拿到开头
     */
    private static final String TOKEN_HEAD = "Bearer ";

    /**
     * JWT签名使用的密钥
     */
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * JWT签名解析器
     */
    public static final JwtParser PARSER = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();

    /**
     * 根据账号名称生成token
     */
    public String generateToken(String accountName) {
        Claims claims = generateClaims(accountName);
        return TOKEN_HEAD + Jwts.builder()
                .setClaims(claims)
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * 解析账号名称
     *
     * @param token 客户端传入的token
     */
    public String parseAccountName(String token) {
        Assert.isTrue(token.startsWith(TOKEN_HEAD), "无效token");
        token = token.substring(TOKEN_HEAD.length());
        try {
            return PARSER.parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new BizException("无效token");
        }
    }

    private Claims generateClaims(String accountName) {
        Claims claims = new DefaultClaims();
        claims.setSubject(accountName);

        LocalDateTime issuedAt = LocalDateTime.now();
        claims.setIssuedAt(Convert.toDate(issuedAt));

        LocalDateTime expiration = issuedAt.plusDays(1);
        claims.setExpiration(Convert.toDate(expiration));

        return claims;
    }
}
