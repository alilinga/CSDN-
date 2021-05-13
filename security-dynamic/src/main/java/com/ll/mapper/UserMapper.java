package com.ll.mapper;

import com.ll.bean.Role;
import com.ll.bean.User;

import java.util.List;

public interface UserMapper {

    User loadUserByUsername(String s);

    List<Role> getRolesById(User user);
}
