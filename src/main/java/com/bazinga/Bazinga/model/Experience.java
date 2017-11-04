package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long experienceId;
    String companyName;
    String companyRole;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @OneToMany
    private List<UserExperience> userExperiences;

}
