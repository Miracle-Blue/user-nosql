package com.example.usernosql.service;

import com.example.usernosql.models.UserModel;
import com.example.usernosql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(String id) {
        Optional<UserModel> optionalUserModel = userRepository.findById(id);
        return optionalUserModel.orElse(null);
    }

    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel editUser(String userId, UserModel user) {
        UserModel updatedUser = getUserById(userId);

        if (updatedUser != null) {
            updatedUser.setAddress(user.getAddress());
            updatedUser.setCompany(user.getCompany());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setName(user.getName());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setWebsite(user.getWebsite());

            userRepository.save(updatedUser);
            return updatedUser;
        }

        return null;
    }

    public boolean deleteUser(String userId) {
        UserModel user = getUserById(userId);

        if (user == null) {
            return  false;
        }

        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
