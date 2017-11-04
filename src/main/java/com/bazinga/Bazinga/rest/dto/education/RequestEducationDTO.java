package com.bazinga.Bazinga.rest.dto.education;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by lazap on 11/4/2017.
 */
@Data
@NoArgsConstructor
public class RequestEducationDTO {
    private Long id;
    @NotNull
    private String school;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
