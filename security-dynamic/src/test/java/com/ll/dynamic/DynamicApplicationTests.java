package com.ll.dynamic;

import com.ll.bean.Menu;
import com.ll.bean.Role;
import com.ll.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.SecurityConfig;

import java.util.List;

@SpringBootTest
class DynamicApplicationTests {

	@Autowired
	MenuService menuService;

	@Test
	void contextLoads() {
		List<Menu> menus = menuService.getAllMenus();
		System.out.println(menus);
		for (Menu menu : menus) {
			List<Role> roles = menu.getRoles();
			String[] roleStr = new String[roles.size()];
			for (int i = 0; i < roles.size(); i++) {
				roleStr[i] = roles.get(i).getName();
			}
		}
	}

}
