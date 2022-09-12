package com.firm.wham.starter.config.security;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.Response;
import com.firm.wham.infrastructure.account.AccountDO;
import com.firm.wham.infrastructure.account.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author ricardo zhou
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AccountGateway accountGateway;
    @Value("${security.white.list}")
    private String[] whiteList;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<AccountDO> optional = accountGateway.findBy(username);
            AccountDO accountDO = optional.orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
            return User.builder().username(accountDO.getName()).password(accountDO.getMd5Password()).authorities("TEST").build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
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
                .authenticationEntryPoint(martAuthenticationEntryPoint());
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

    private static void setResponseContent(HttpServletResponse httpServletResponse, Response response) throws IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(response));
        httpServletResponse.getWriter().flush();
    }

}