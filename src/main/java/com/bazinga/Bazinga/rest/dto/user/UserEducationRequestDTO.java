package com.bazinga.Bazinga.rest.dto.user;

import com.bazinga.Bazinga.model.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEducationRequestDTO {
    @NotNull
    private String school;
    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;
    @NotNull
    private EducationLevel level;
}
