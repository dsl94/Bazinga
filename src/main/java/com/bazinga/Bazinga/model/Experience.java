package com.bazinga.Bazinga.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    Long id;
    String companyName;
    String roleInCompany;
    LocalDate startDate;
    LocalDate endDate;

}
