package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;

import java.util.List;

public interface UserService {

    RegisterUserResponseDTO register(RegisterUserDTO request) throws UserException;
}