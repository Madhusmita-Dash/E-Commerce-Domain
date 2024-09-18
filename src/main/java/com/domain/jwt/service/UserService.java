package com.domain.jwt.service;

import com.domain.jwt.dao.RoleDao;
import com.domain.jwt.dao.UserDao;
import com.domain.jwt.entity.Role;
import com.domain.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public User registerNewUser(User user) {
        return userDao.save(user);
    }
    public void initRolesUser(){
       Role adminRole= new Role();
       adminRole.setRoleName("Admiin");
       adminRole.setDescription("Admin");
       roleDao.save(adminRole);

        Role userRole= new Role();
        userRole.setRoleName("User");
        userRole.setDescription("Default Role for newly created record");
        roleDao.save(userRole);

        User adminUser=new User();
        adminUser.setUserfirst_name("admin");
        adminUser.setUserlast_name("admin");
        adminUser.setUsername("admin123");
        adminUser.setPassword("admin@123");
        Set<Role> adminroles=new HashSet<>();
        adminroles.add(adminRole);
        userDao.save(adminUser);

        User user=new User();
        user.setUserfirst_name("raj");
        user.setUserlast_name("Dash");
        user.setUsername("raj123");
        user.setPassword("raj@123");

        Set<Role> userroles=new HashSet<>();
        userroles.add(userRole);
        userDao.save(user);




    }
}
