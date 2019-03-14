package com.hengyh.blog2.security;

import com.hengyh.blog2.User;
import com.hengyh.blog2.vo.RegistrationForm;

/**
 * 注册用户的服务
 */
public interface UserRegistrationService {
    User registration(RegistrationForm registrationForm);
}
