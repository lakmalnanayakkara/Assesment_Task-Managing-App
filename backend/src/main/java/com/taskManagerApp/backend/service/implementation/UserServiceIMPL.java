package com.taskManagerApp.backend.service.implementation;

import com.taskManagerApp.backend.dto.request.UserSignInDTO;
import com.taskManagerApp.backend.dto.request.UserSignUpDTO;
import com.taskManagerApp.backend.dto.response.UserResponseDTO;
import com.taskManagerApp.backend.entity.User;
import com.taskManagerApp.backend.exception.AlreadyExistException;
import com.taskManagerApp.backend.exception.NotFoundException;
import com.taskManagerApp.backend.repository.UserRepository;
import com.taskManagerApp.backend.service.UserService;
import com.taskManagerApp.backend.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO userSignUp(UserSignUpDTO userSignUpDTO) {
        User user = userRepository.findUserByUsername(userSignUpDTO.getUsername());
        if(user == null) {
            String hashedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());
            userSignUpDTO.setPassword(hashedPassword);
            User newUser = userMapper.userSignUpDTOToUser(userSignUpDTO);
            userRepository.save(newUser);
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    newUser.getUserId(),
                    newUser.getUsername(),
                    ""
            );
            return userResponseDTO;
        }else{
            throw new AlreadyExistException("Username already exists.");
        }
    }

    @Override
    public UserResponseDTO userSignIn(UserSignInDTO userSignInDTO) {
        User user = userRepository.findUserByUsername(userSignInDTO.getUsername());
        if(user != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getUserId(),
                    user.getUsername(),
                    ""
            );
            return userResponseDTO;
        }else{
            throw new NotFoundException("Username doesn't exist.");
        }
    }
}
