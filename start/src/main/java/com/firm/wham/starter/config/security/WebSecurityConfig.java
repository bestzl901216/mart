package com.firm.wham.starter.config.security;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.Response;
import com.firm.wham.domain.account.TokenUtil;
import com.firm.wham.domain.security.Authentication;
import com.firm.wham.domain.security.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author ricardo zhou
 */
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    public static final String HEADER = "Authorization";
    private final AuthenticationRepository authenticationRepository;
    @Value("${security.white.list}")
    private String[] whiteList;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, OncePerRequestFilter tokenFilter) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        // 任何请求需要身份认证
        registry.antMatchers(whiteList)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(martAccessDeniedHandler())
                .authenticationEntryPoint(martAuthenticationEntryPoint())
                // 自定义过滤器必须早于security的验证过滤器
                .and()
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AccessDeniedHandler martAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> setResponseContent(response, Response.buildFailure("SECURITY_ERROR", "禁止访问未授权资源"));
    }

    @Bean
    public AuthenticationEntryPoint martAuthenticationEntryPoint() {
        return (request, response, authException) -> setResponseContent(response, Response.buildFailure("SECURITY_ERROR", "未登录或者登录信息已失效"));

    }

    @Bean
    public OncePerRequestFilter tokenFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String token = request.getHeader(HEADER);
                String accountName = token == null ? null : TokenUtil.parseAccountName(token);
                log.info("checking accountName:{}", accountName);
                Optional<Authentication> authenticationOptional = authenticationRepository.find(accountName);
                if (authenticationOptional.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Authentication authentication = authenticationOptional.get();
                    UserDetails userDetails = User.builder().username(authentication.getAccountName()).password(authentication.getEncodedPassword()).authorities(authentication.getAuthorities()).build();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated accountName:{}", accountName);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private static void setResponseContent(HttpServletResponse httpServletResponse, Response response) throws IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(response));
        httpServletResponse.getWriter().flush();
    }

}
