package com.domain.jwt.controller;

import com.domain.jwt.entity.Role;
import com.domain.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 

public class RoleController {

    //contain end point for different rolls

    @Autowired
    private RoleService roleService;
    @PostMapping({"/createNewRole"}) //new end point(createNewRole)
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);

    }
}
