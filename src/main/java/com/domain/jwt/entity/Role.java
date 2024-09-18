package com.domain.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
//getter and settered
    @Id
    private String roleName;
    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
