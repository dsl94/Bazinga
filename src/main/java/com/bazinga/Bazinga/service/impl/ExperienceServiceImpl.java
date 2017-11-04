package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.model.Experience;
import com.bazinga.Bazinga.repository.ExperienceRepository;
import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.experience.ResponseExperienceDTO;
import com.bazinga.Bazinga.service.BaseService;
import com.bazinga.Bazinga.service.ExperienceService;
import com.bazinga.Bazinga.util.ExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Service
public class ExperienceServiceImpl extends BaseService implements ExperienceService {

    @Autowired
    private ExperienceRepository repository;

    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    public ResponseExperienceDTO getOne(Long id) {
        return experienceMapper.mapFromEntity(repository.getOne(id));
    }

    @Override
    public List<ResponseExperienceDTO> getAll() {
        List<Experience> experiences=repository.findAll();
        List<ResponseExperienceDTO> listResponseExperienceDTO=new ArrayList<>();
        for (Experience experience:experiences){
            listResponseExperienceDTO.add(experienceMapper.mapFromEntity(experience));
        }
        return listResponseExperienceDTO;
    }

    @Override
    public ResponseExperienceDTO create(RequestExperienceDTO requestExperienceDTO) {
        Experience experience=experienceMapper.mapFromRequest(requestExperienceDTO);
        return experienceMapper.mapFromEntity(repository.save(experience));
    }

    @Override
    public ResponseExperienceDTO update(RequestExperienceDTO requestExperienceDTO) {
        Experience experience=experienceMapper.mapFromRequest(requestExperienceDTO);
        return experienceMapper.mapFromEntity(repository.save(experience));
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
