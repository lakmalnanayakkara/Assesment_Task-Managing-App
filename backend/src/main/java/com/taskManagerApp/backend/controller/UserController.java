package com.taskManagerApp.backend.controller;

import com.taskManagerApp.backend.dto.StandardResponse;
import com.taskManagerApp.backend.dto.request.UserSignInDTO;
import com.taskManagerApp.backend.dto.request.UserSignUpDTO;
import com.taskManagerApp.backend.dto.response.UserResponseDTO;
import com.taskManagerApp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<StandardResponse> userSignUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        UserResponseDTO userResponseDTO = userService.userSignUp(userSignUpDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(201,"SIGN UP SUCCESSFUL",userResponseDTO),
                HttpStatus.CREATED
        );
        return response;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<StandardResponse> userSignUp(@RequestBody UserSignInDTO userSignInDTO) {
        UserResponseDTO userResponseDTO = userService.userSignIn(userSignInDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(200,"SIGN IN SUCCESSFUL",userResponseDTO),
                HttpStatus.CREATED
        );
        return response;
    }
}
