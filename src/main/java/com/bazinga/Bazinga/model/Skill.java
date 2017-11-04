package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by lazap on 11/4/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "skill")
    private List<UserSkills> userSkills;
}
