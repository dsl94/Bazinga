package com.bazinga.Bazinga.util;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.rest.dto.education.RequestEducationDTO;
import com.bazinga.Bazinga.rest.dto.education.ResponseEducationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by lazap on 11/4/2017.
 */
@Component
public class EducationMapper {

    public Education mapRequestToEntity(RequestEducationDTO requestEducationDTO){
        Education education = new Education();
        education.setId(requestEducationDTO.getId());
        education.setSchool(requestEducationDTO.getSchool());
        education.setStartDate(requestEducationDTO.getStartDate());
        education.setEndDate(requestEducationDTO.getEndDate());
        return education;
    }

    public ResponseEducationDTO mapResponseToDTO(Education education){
        ResponseEducationDTO responseEducationDTO = new ResponseEducationDTO();
        responseEducationDTO.setId(education.getId());
        responseEducationDTO.setSchool(education.getSchool());
        responseEducationDTO.setStartDate(education.getStartDate());
        responseEducationDTO.setEndDate(education.getEndDate());
        return responseEducationDTO;
    }
}
