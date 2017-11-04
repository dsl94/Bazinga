package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.model.Skill;

import java.util.List;

/**
 * Created by lazap on 11/4/2017.
 */
public interface SkillService {
    Skill getOne(Long id);
    List<Skill> getAll();
    Skill create(Skill skill);
    Skill update(Skill skill);
    void delete(Long id);
}
