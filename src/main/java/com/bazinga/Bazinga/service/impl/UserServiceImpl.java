package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.error.ErrorCode;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.model.Experience;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.repository.EducationRepository;
import com.bazinga.Bazinga.repository.ExperienceRepository;
import com.bazinga.Bazinga.repository.SkillRepository;
import com.bazinga.Bazinga.repository.UserRepository;
import com.bazinga.Bazinga.rest.dto.education.RequestEducationDTO;
import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.experience.ResponseExperienceDTO;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.rest.dto.user.UserEducationRequestDTO;
import com.bazinga.Bazinga.service.UserService;
import com.bazinga.Bazinga.util.EducationMapper;
import com.bazinga.Bazinga.util.ExperienceMapper;
import com.bazinga.Bazinga.util.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceMapper experienceMapper;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private EducationMapper educationMapper;

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

    @Override
    public CandidateProfileDTO getCandidateProfile() throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // First do validation
        // Check if user exist
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user != null){
            CandidateProfileDTO profileDTO = new CandidateProfileDTO();
            profileDTO.setId(user.getId());
            profileDTO.setEmail(user.getEmail());
            profileDTO.setName(user.getName());
            profileDTO.setUsername(user.getUsername());
            profileDTO.setUserSkills(user.getUserSkills());
            if(user.getEducation() != null)
                profileDTO.setUserEducation(educationMapper.mapResponseToDTO(user.getEducation()));
            Set<ResponseExperienceDTO> responseExperienceDTOs=new HashSet<>();
            for (Experience experience : user.getExperiences()){
                responseExperienceDTOs.add(experienceMapper.mapFromEntity(experience));
            }
            profileDTO.setUserExperience(responseExperienceDTOs);

            return profileDTO;
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
    }

    @Override
    public CandidateProfileDTO addExperiences(List<RequestExperienceDTO> userExperiences) throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsernameIgnoreCase(username);
        if(user != null){
            user.getExperiences().clear();
            for(RequestExperienceDTO experienceDTO : userExperiences){
                Experience experience = experienceMapper.mapFromRequest(experienceDTO);
                experienceRepository.save(experience);
                user.getExperiences().add(experience);
            }
            userRepository.save(user);
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }

        return getCandidateProfile();
    }

    @Override
    public CandidateProfileDTO addLocations(List<String> locations) throws UserException {
        String locationsFormatted = StringUtils.join(locations, ",");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsernameIgnoreCase(username);
        if(user != null){
            user.setLocations(locationsFormatted);
            userRepository.save(user);
            return getCandidateProfile();
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }


    }


    @Override
    public CandidateProfileDTO addSkillsToUSer(List<String> skillsRequest) throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // First do validation
        // Check if user exist
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User with that id not found");
        }


        Set<Skill> skills=new HashSet<>();
        for (String skillName:skillsRequest){
            Skill skill =skillRepository.findByNameIgnoreCase(skillName);
            if (skill!=null){
                skills.add(skill);
            }else{
                skill = new Skill();
                skill.setName(skillName);
                skill = skillRepository.save(skill);
                skills.add(skill);
            }
        }

        user.setUserSkills(skills);
        userRepository.save(user);

        return getCandidateProfile();
    }

    @Override
    public CandidateProfileDTO addEducation(UserEducationRequestDTO request) throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user != null) {
            // Check if user have education
            if (user.getEducation() != null) {
                throw new UserException(ErrorCode.EDUCATION_EXIST, "User already have education, please edit that one");
//                user.setEducation(educationMapper.mapFromUserEducationRequest(request));
//                return getCandidateProfile();
            } else {
                Education education = educationMapper.mapFromUserEducationRequest(request);
                Education res=educationRepository.save(education);
                user.setEducation(res);
                userRepository.save(user);
                return this.getCandidateProfile();
            }
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
    }

    @Override
    public CandidateProfileDTO updateEducation(RequestEducationDTO requestEducationDTO) throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsernameIgnoreCase(username);

        if(user != null){
            user.setEducation(educationMapper.mapRequestToEntity(requestEducationDTO));
            userRepository.save(user);
            return getCandidateProfile();
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
    }

}
