package com.bazinga.Bazinga.rest.dto.user;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.model.Experience;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.rest.dto.education.ResponseEducationDTO;
import com.bazinga.Bazinga.rest.dto.experience.ResponseExperienceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Set<Skill> userSkills;
    private Set<ResponseExperienceDTO> userExperience;
    private ResponseEducationDTO userEducation;
    private List<String> locations;
}
