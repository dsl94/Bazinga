package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.repository.SkillRepository;
import com.bazinga.Bazinga.service.BaseService;
import com.bazinga.Bazinga.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lazap on 11/4/2017.
 */
public class SkillServiceImpl extends BaseService implements SkillService {

    @Autowired
    private SkillRepository repository;

    @Override
    public Skill getOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Skill> getAll() {
        return repository.findAll();
    }

    @Override
    public Skill create(Skill skill) {
        return repository.save(skill);
    }

    @Override
    public Skill update(Skill skill) {
        return repository.save(skill);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
