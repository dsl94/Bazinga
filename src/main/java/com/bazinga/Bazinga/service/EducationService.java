package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.model.Skill;

import java.util.List;

/**
 * Created by lazap on 11/4/2017.
 */
public interface EducationService {
    Education getOne(Long id);
    List<Education> getAll();
    Education create(Education education);
    Education update(Education education);
    void delete(Long id);
}
