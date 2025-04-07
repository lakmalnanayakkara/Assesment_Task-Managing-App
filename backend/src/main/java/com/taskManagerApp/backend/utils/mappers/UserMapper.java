package com.taskManagerApp.backend.utils.mappers;

import com.taskManagerApp.backend.dto.request.UserSignUpDTO;
import com.taskManagerApp.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userSignUpDTOToUser(UserSignUpDTO userSignUpDTO);
}
