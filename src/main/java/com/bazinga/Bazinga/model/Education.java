package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Created by lazap on 11/4/2017.
 */

@Entity
@Table
@ToString
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String school;
    private LocalDate startDate;
    private LocalDate endDate;
    private EducationLevel level;

    @OneToOne
    @JoinColumn(name= "education_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EducationLevel getLevel() {
        return level;
    }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }

}
