package com.bazinga.Bazinga.rest.dto.offer;

import com.bazinga.Bazinga.model.EducationLevel;
import com.bazinga.Bazinga.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferResponseDTO {
    private Long userId;
    private boolean active;
    private String title;
    private List<Skill> skills;
    private List<String> locations;
    private EducationLevel minimumEducation;
    private String description;
    private Integer minYearsOfExperience;
}
