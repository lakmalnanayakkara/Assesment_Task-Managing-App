package com.taskManagerApp.backend.service.implementation;

import com.taskManagerApp.backend.repository.UserRepository;
import com.taskManagerApp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
}
