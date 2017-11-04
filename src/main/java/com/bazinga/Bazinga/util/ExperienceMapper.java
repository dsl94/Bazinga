package com.bazinga.Bazinga.util;

import com.bazinga.Bazinga.model.Experience;
import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.experience.ResponseExperienceDTO;
import org.springframework.stereotype.Component;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Component
public class ExperienceMapper {

    public Experience mapFromRequest(RequestExperienceDTO requestExperienceDTO){
        Experience experience=new Experience();

        experience.setCompanyName(requestExperienceDTO.getCompanyName());
        experience.setRoleInCompany(requestExperienceDTO.getRoleInCompany());
        experience.setStartDate(requestExperienceDTO.getStartDate());
        experience.setEndDate(requestExperienceDTO.getEndDate());

        return experience;
    }

    public ResponseExperienceDTO mapFromEntity(Experience experience){
        ResponseExperienceDTO responseExperienceDTO=new ResponseExperienceDTO();

        responseExperienceDTO.setId(experience.getExperienceId());
        responseExperienceDTO.setCompanyName(experience.getCompanyName());
        responseExperienceDTO.setRoleInCompany(experience.getRoleInCompany());
        responseExperienceDTO.setStartDate(experience.getStartDate());
        responseExperienceDTO.setEndDate(experience.getEndDate());

        return responseExperienceDTO;
    }
}
