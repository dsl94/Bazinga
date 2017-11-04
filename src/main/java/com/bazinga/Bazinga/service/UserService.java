package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.rest.dto.education.RequestEducationDTO;
import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.rest.dto.user.UserEducationRequestDTO;

import java.util.List;

public interface UserService {

    RegisterUserResponseDTO register(RegisterUserDTO request) throws UserException;
    CandidateProfileDTO addSkillsToUSer(List<String> skills) throws  UserException;
    CandidateProfileDTO getCandidateProfile() throws UserException;
    CandidateProfileDTO addEducation(UserEducationRequestDTO request) throws UserException;
    CandidateProfileDTO addExperiences(List<RequestExperienceDTO> userExperiences) throws UserException;
    CandidateProfileDTO addLocations(List<String> locations) throws UserException;
    CandidateProfileDTO updateEducation(RequestEducationDTO requestEducationDTO) throws UserException;
}
