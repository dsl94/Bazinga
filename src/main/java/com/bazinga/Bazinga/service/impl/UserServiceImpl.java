package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.error.ErrorCode;
import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.model.Authority;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.repository.SkillRepository;
import com.bazinga.Bazinga.repository.UserRepository;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.security.AuthoritiesConstants;
import com.bazinga.Bazinga.service.UserService;
import com.bazinga.Bazinga.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SkillRepository skillRepository;

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
            profileDTO.setUserEducation(user.getEducation());
            profileDTO.setUserExperience(user.getExperiences());

            return profileDTO;
        } else {
            throw new UserException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
    }

    @Override
    public CandidateProfileDTO addSkillsToUSer(List<String> skillsRequest) throws OfferException, UserException {
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
}