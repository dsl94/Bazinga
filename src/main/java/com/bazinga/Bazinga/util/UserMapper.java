package com.bazinga.Bazinga.util;

import com.bazinga.Bazinga.model.Authority;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.rest.dto.user.UserEducationRequestDTO;
import com.bazinga.Bazinga.security.AuthoritiesConstants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User mapFromRegisterRequest(RegisterUserDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(encoder.encode(request.getPassword()));
        Set<Authority> authorities = new HashSet<>();
        if (request.getAuthority().equalsIgnoreCase("CANDIDATE")){
            Authority authority = new Authority();
            authority.setName(AuthoritiesConstants.CANDIDATE);
            authorities.add(authority);
            user.setAuthorities(authorities);
        } else {
            Authority authority = new Authority();
            authority.setName(AuthoritiesConstants.COMPANY);
            authorities.add(authority);
            user.setAuthorities(authorities);
        }

        return user;
    }

    public RegisterUserResponseDTO mapRegisterResponseFromEntity(User user) {
        RegisterUserResponseDTO userResponseDTO = new RegisterUserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());

        return userResponseDTO;
    }
}