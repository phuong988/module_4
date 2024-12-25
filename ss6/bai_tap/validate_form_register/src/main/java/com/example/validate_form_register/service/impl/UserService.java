package com.example.validate_form_register.service.impl;

import com.example.validate_form_register.model.User;
import com.example.validate_form_register.repository.UserRepository;
import com.example.validate_form_register.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
