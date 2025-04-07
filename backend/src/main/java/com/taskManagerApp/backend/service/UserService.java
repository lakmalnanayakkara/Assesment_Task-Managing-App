package com.taskManagerApp.backend.service;

import com.taskManagerApp.backend.dto.request.UserSignInDTO;
import com.taskManagerApp.backend.dto.request.UserSignUpDTO;
import com.taskManagerApp.backend.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO userSignUp(UserSignUpDTO userSignUpDTO);

    UserResponseDTO userSignIn(UserSignInDTO userSignInDTO);
}
