package com.bazinga.Bazinga.rest.dto.user;

import com.bazinga.Bazinga.model.Skill;

import java.util.Set;

public class CandidateProfileDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Set<Skill> usersSkills;

}
