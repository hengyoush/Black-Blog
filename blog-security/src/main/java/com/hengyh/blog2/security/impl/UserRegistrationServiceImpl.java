package com.hengyh.blog2.security.impl;

import com.hengyh.blog2.User;
import com.hengyh.blog2.dao.UserRepository;
import com.hengyh.blog2.security.UserRegistrationService;
import com.hengyh.blog2.vo.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @Autowired
    public UserRegistrationServiceImpl(PasswordEncoder encoder,
                                       UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public User registration(RegistrationForm registrationForm) {
        User user = new User(registrationForm.getUsername(),
                encoder.encode(registrationForm.getPassword()));
        userRepository.save(user);
        return user;
    }
}
