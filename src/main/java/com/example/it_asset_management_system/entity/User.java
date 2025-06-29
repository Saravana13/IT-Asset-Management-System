package com.example.it_asset_management_system.entity;

import java.util.List;

public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    private Role role;

    private List<AssetAssignment> assignments;
}
