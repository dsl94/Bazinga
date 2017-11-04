package com.bazinga.Bazinga.repository;

import com.bazinga.Bazinga.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lazap on 11/4/2017.
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
