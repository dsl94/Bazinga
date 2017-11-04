package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;

import java.util.List;

public interface UserService {

    RegisterUserResponseDTO register(RegisterUserDTO request) throws UserException;
    CandidateProfileDTO addSkillsToUSer(List<String> skills) throws OfferException, UserException;
    CandidateProfileDTO getCandidateProfile() throws UserException;
}
