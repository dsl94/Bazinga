package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.error.ErrorCode;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.repository.UserRepository;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.service.UserService;
import com.bazinga.Bazinga.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RegisterUserResponseDTO register(RegisterUserDTO request) throws UserException {

        if (userRepository.findByUsernameIgnoreCase(request.getUsername()) != null) {
            throw new UserException(ErrorCode.USERNAME_EXIST, "User with that username already exist");
        }

        if (userRepository.findByEmailIgnoreCase(request.getEmail()) != null) {
            throw new UserException(ErrorCode.EMAIL_EXIST, "User with that email already exist");
        }

        User forSave = userMapper.mapFromRegisterRequest(request);
        User result = userRepository.save(forSave);

        return userMapper.mapRegisterResponseFromEntity(result);
    }
}