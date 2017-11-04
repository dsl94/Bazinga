package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.rest.dto.user.UserEducationRequestDTO;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.List;

public interface UserService {

    RegisterUserResponseDTO register(RegisterUserDTO request) throws UserException;
    CandidateProfileDTO getCandidateProfile() throws UserException;
    CandidateProfileDTO addEducation(UserEducationRequestDTO request) throws UserException;
}
