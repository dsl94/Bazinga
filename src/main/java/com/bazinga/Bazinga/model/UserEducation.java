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
public class UserEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;

}