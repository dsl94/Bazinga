package com.bazinga.Bazinga.rest.dto.experience;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Data
@NoArgsConstructor
public class ResponseExperienceDTO {

    private Long id;
    private String companyName;
    private String roleInCompany;
    private LocalDate startDate;
    private LocalDate endDate;
}
