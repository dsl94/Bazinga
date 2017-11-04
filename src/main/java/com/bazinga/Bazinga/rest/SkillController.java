package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lazap on 11/4/2017.
 */
@RestController
@RequestMapping(value = "/api/skill")
public class SkillController extends BaseController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity getAll(){
        return response(skillService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity getOne(@PathVariable("id") Long id){
        return response(skillService.getOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity create(@RequestBody Skill skill){
        return response(skillService.create(skill));
    }

    @RequestMapping(method = RequestMethod.PUT)
    private ResponseEntity update(@RequestBody Skill skill){
        return response(skillService.update(skill));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResponseEntity delete(@PathVariable("id") Long id){
        skillService.delete(id);
        return response();
    }


}
