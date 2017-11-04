package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@RestController
@RequestMapping("/api/candidate/experience")
public class ExperienceController extends BaseController{

    @Autowired
    private ExperienceService experienceService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity getExperience(@PathVariable Long id){
        return response(experienceService.getOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllExperiences(){
        return response(experienceService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createExperience(@RequestBody RequestExperienceDTO requestExperienceDTO){
        return response(experienceService.create(requestExperienceDTO));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateExperience(@RequestBody RequestExperienceDTO requestExperienceDTO){
        return response(experienceService.update(requestExperienceDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResponseEntity deleteExperience(@PathVariable("id") Long id){
        experienceService.delete(id);
        return response();
    }
}
