package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
@Data
@NoArgsConstructor
@Table
@Embeddable
public class MatchedSkills {

    @OneToOne
    private Skill skill;
}
