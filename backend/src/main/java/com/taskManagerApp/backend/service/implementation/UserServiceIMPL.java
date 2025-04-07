package com.taskManagerApp.backend.service.implementation;

import com.taskManagerApp.backend.dto.request.UserSignInDTO;
import com.taskManagerApp.backend.dto.request.UserSignUpDTO;
import com.taskManagerApp.backend.dto.response.UserResponseDTO;
import com.taskManagerApp.backend.entity.User;
import com.taskManagerApp.backend.exception.AlreadyExistException;
import com.taskManagerApp.backend.exception.BadCredentialsException;
import com.taskManagerApp.backend.exception.NotFoundException;
import com.taskManagerApp.backend.repository.UserRepository;
import com.taskManagerApp.backend.service.UserService;
import com.taskManagerApp.backend.utils.JwtUtil;
import com.taskManagerApp.backend.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceIMPL implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserResponseDTO userSignUp(UserSignUpDTO userSignUpDTO) {
        User user = userRepository.findUserByUsername(userSignUpDTO.getUsername());
        if(user == null) {
            String hashedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());
            userSignUpDTO.setPassword(hashedPassword);
            User newUser = userMapper.userSignUpDTOToUser(userSignUpDTO);
            userRepository.save(newUser);
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    newUser.getUserId(),
                    newUser.getUsername(),
                    token
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
            authenticate(userSignInDTO.getUsername(), userSignInDTO.getPassword());
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getUserId(),
                    user.getUsername(),
                    token
            );
            return userResponseDTO;
        }else{
            throw new NotFoundException("Username doesn't exist.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }else {
            throw new NotFoundException("Username doesn't exist.");
        }
    }

    private void authenticate(String username,String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (Exception e){
            throw new BadCredentialsException(e.getMessage());
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase()));
        return authorities;
    }
}
