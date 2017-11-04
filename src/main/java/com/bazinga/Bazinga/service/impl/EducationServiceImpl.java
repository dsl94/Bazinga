package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.repository.EducationRepository;
import com.bazinga.Bazinga.service.BaseService;
import com.bazinga.Bazinga.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lazap on 11/4/2017.
 */
@Service
public class EducationServiceImpl extends BaseService implements EducationService {

    @Autowired
    private EducationRepository repository;

    @Override
    public Education getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Education> getAll() {
        return repository.findAll();
    }

    @Override
    public Education create(Education education) {
        return repository.save(education);
    }

    @Override
    public Education update(Education education) {
        return repository.save(education);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
