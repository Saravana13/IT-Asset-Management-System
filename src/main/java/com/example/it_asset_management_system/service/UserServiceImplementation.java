package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Role;
import com.example.it_asset_management_system.entity.User;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;
import com.example.it_asset_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User addUser(String username, String email, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id,String username, String email, String password, String role) throws UserNotFoundException {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        User user = userRepository.findById(id).get();
        if(username!=null){
            user.setUsername(username);
        }
        if(email!=null){
            user.setEmail(email);
        }
        if(password!=null){
            user.setPassword(password);
        }
        if(role!=null){
            user.setRole(Role.valueOf(role));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }
}
