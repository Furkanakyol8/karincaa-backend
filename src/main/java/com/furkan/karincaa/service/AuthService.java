package com.furkan.karincaa.service;

import com.furkan.karincaa.model.dto.*;

public interface AuthService {

    LoginDto login(LoginRequest loginRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
