package com.ll.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.bean.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String url, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(url));
        // 做校验的核心类authenticationManager
        setAuthenticationManager((authenticationManager));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        User user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

    // 登录成功
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取登录用户的角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer stringBuffer = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            stringBuffer.append(authority.getAuthority()).append(",");
        }
        // 生成Jwt
        String jwt=Jwts.builder()
                // 配置用户角色
                .claim("authorities",stringBuffer)
                .setSubject(authResult.getName())
                .setExpiration(new Date((System.currentTimeMillis()+60*60*1000)))
                .signWith(SignatureAlgorithm.HS512,"admin@123")
                .compact();
        // 把token写到前面去
        Map<String,String> map =new HashMap<>();
        map.put("token",jwt);
        map.put("msg","login sucess");
        response.setContentType("application/json;charser=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        Map<String,String> map =new HashMap<>();
        map.put("msg","login fail");
        response.setContentType("application/json;charser=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}
