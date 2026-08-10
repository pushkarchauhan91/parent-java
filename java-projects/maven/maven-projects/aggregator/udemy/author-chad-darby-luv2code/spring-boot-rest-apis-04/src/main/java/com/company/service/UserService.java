package com.company.service;

import com.company.request.PasswordUpdateRequest;
import com.company.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
