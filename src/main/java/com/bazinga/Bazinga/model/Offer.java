package com.bazinga.Bazinga.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String locations;
    private String description;
    private EducationLevel minimumLevel;
    private Integer minYearsOfExperience;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "offer_skills",
            joinColumns = {@JoinColumn(name = "offer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    private Set<Skill> skills = new HashSet<>();
}
