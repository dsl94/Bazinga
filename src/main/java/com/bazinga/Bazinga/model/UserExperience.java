package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Table
@Entity
@Data
@NoArgsConstructor
public class UserExperience {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name="skill_id", nullable=false)
    private Experience experienceId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
