package com.example.it_asset_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "users")

public class User extends BaseModel{

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<AssetAssignment> assignments;
}
