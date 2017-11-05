package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "matches")
public class Match implements Comparable<Match>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private Offer offer;
    private double skillPercentage;

    @ElementCollection
    @CollectionTable(name="matched_skills", joinColumns = @JoinColumn(name="match_id"))
    @AttributeOverrides({
            @AttributeOverride(name="skill",column = @Column(name="skill_id"))
    })
    private Set<MatchedSkills> matchedSkills;


    @Override
    public int compareTo(Match o) {
        if (this.getSkillPercentage() > o.getSkillPercentage()) {
            return 1;
        } else if (this.getSkillPercentage() < o.getSkillPercentage()) {
            return -1;
        } else {
            return 0;
        }
    }
}
