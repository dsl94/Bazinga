package com.bazinga.Bazinga.rest.dto;

import com.bazinga.Bazinga.model.MatchedSkills;
import com.bazinga.Bazinga.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDTO {

    private Double percentage;
    private String userName;
    private Set<MatchedSkills> skills;
}
