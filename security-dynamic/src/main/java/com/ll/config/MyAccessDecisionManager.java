package com.ll.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    // authentication保存当前登录用户信息，知道有哪些角色
    // Object获取当前请求对象，知道需要哪些角色
    // collection是Myfilter.java里的getAttributes返回值
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            // 地址没匹配上
            if("ROLE_login".equals(configAttribute.getAttribute())){
                // 是否登录：AnonymousAuthenticationToken匿名用户未登录
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("非法请求");
                }else {
                    return;
                }

            }
            // 这里自动在前面加上了ROLE_
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 如果ABC三种角色，满足其一就可以访问
            // 根据业务逻辑，ABC三种角色均满足才可访问，需要修改for循环
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals("ROLE_"+configAttribute.getAttribute())){
                    return;
                }
            }
            // 不是所需要的角色
            throw new AccessDeniedException("非法请求");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
