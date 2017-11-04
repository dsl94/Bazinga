package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.experience.ResponseExperienceDTO;

import java.util.List;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
public interface ExperienceService {

    ResponseExperienceDTO getOne(Long id);
    List<ResponseExperienceDTO> getAll();
    ResponseExperienceDTO create(RequestExperienceDTO requestExperienceDTO);
    ResponseExperienceDTO update(RequestExperienceDTO requestExperienceDTO);
    void delete(Long id);
}
