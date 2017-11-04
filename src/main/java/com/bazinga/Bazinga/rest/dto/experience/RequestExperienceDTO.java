package com.bazinga.Bazinga.rest.dto.experience;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Data
@NoArgsConstructor
public class RequestExperienceDTO {

    @NotNull
    private String companyName;
    @NotNull
    private String roleInCompany;
    @NotNull
    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
