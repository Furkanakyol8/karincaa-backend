package com.furkan.karincaa.service.impl;

import com.furkan.karincaa.exception.CustomAuthenticationException;
import com.furkan.karincaa.model.dto.*;
import com.furkan.karincaa.model.entity.User;
import com.furkan.karincaa.repository.UserRepository;
import com.furkan.karincaa.security.helper.JwtHelper;
import com.furkan.karincaa.security.helper.UserHelper;
import com.furkan.karincaa.service.AuthService;
import com.furkan.karincaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;


    @Override
    public LoginDto login(LoginRequest loginRequest) {
        User foundUser = userService.findByPhone(loginRequest.getPhone());

        if(!passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())) {
            throw new CustomAuthenticationException("Wrong password!");
        }

        String token = jwtHelper.generateToken(foundUser);

        return new LoginDto(foundUser, token);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User loggedUser = UserHelper.getLoggedUser();

        if(!passwordEncoder.matches(changePasswordRequest.oldPassword(), loggedUser.getPassword())){
            throw new CustomAuthenticationException("Wrong password!");
        }

        String encodedNewPassword = passwordEncoder.encode(changePasswordRequest.newPassword());
        loggedUser.setPassword(encodedNewPassword);

        userRepository.save(loggedUser);
    }
}
