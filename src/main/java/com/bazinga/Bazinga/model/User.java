package com.bazinga.Bazinga.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class
User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String locations;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_skills",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    private Set<Skill> userSkills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_experience",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "experience_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    private Set<Experience> experiences;

    @OneToOne
    private Education education;

    @OneToMany(mappedBy = "user")
    private List<Offer> offers;

    @OneToMany(mappedBy = "user")
    private List<Match> matches;

    @Override
    public String toString(){
        return id.toString();
    }

}
