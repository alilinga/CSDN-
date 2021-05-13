package com.ll.config;

import com.ll.bean.Menu;
import com.ll.bean.Role;
import com.ll.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class SercurityDynamicfilter implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher pathMatcher =new AntPathMatcher();
    @Autowired
    MenuService menuService;

    // 根据请求地址，分析地址需要哪些角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 得到请求的地址
        String url = ((FilterInvocation) o).getRequestUrl();
        // menu常用但是不改变，可以把查到的menu存到缓存里
        List<Menu> menus = menuService.getAllMenus();
        for (Menu menu : menus) {
            if(pathMatcher.match(menu.getPattern(),url)){
                List<Role> roles = menu.getRoles();
                String[] roleStr=new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleStr[i]=roles.get(i).getName();
                }
                // 里面存的列表就是自己所需要的角色
                return SecurityConfig.createList(roleStr);
            }
        }
        // 其他地址的处理
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
