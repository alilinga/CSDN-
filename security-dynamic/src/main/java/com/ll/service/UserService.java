package com.ll.service;

import com.ll.bean.User;
import com.ll.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.loadUserByUsername(s);
        if (null==user){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(userMapper.getRolesById(user));
        return user;
    }
}
