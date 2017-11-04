package com.bazinga.Bazinga.match.model;

import com.bazinga.Bazinga.model.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by lazap on 11/4/2017.
 */
@Data
@NoArgsConstructor
public class SkillMatcherResponse {
    private Double matchedPercentage;
    private Set<Skill> matchedSkills;
}
