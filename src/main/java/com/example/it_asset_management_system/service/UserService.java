package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.User;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;

import java.util.List;


public interface UserService {

    User addUser(String username, String email, String password,String role);
    User updateUser(int id,String username, String email, String password, String role)throws UserNotFoundException;
    boolean deleteUser(int id);
    List<User> getAllUsers();
    User getUserById(int id);

}
