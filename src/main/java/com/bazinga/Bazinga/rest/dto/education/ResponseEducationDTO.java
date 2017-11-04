package com.bazinga.Bazinga.rest.dto.education;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by lazap on 11/4/2017.
 */
@Data
@NoArgsConstructor
public class ResponseEducationDTO {
    private Long id;
    private String school;
    private String startDate;
    private String endDate;
}
