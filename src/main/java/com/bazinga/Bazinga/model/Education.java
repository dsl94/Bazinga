package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by lazap on 11/4/2017.
 */

@Entity
@Data
@NoArgsConstructor
@Table
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String school;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany
    private List<UserEducation> userEducations;
}
