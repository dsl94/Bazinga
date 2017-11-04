package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by lazap on 11/4/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table
public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "skill_id")
    private Skill skill;
}