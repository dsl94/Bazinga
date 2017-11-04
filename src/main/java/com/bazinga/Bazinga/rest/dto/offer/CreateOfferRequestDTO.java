package com.bazinga.Bazinga.rest.dto.offer;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.model.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferRequestDTO {
    @NotNull
    private String title;
    @NotNull
    private List<String> skills;
    @NotNull
    private EducationLevel minimumLevel;
    @NotNull
    private List<String> locations;
    @NotNull
    private Integer minYearsOfExperience;

    private String description;


}
