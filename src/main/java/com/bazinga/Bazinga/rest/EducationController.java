package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.rest.dto.education.RequestEducationDTO;
import com.bazinga.Bazinga.service.EducationService;
import com.bazinga.Bazinga.util.EducationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lazap on 11/4/2017.
 */
@RestController
@RequestMapping(value = "/api/candidate/education")
public class EducationController extends BaseController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private EducationMapper educationMapper;

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity getAll(){
        return response(educationService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity getOne(@PathVariable("id") Long id){
        return response(educationService.getOne(id));
    }

    /*@RequestMapping(method = RequestMethod.PUT)
    private ResponseEntity update(@RequestBody RequestEducationDTO requestEducationDTO){
        return response(educationService.update(educationMapper.mapRequestToEntity(requestEducationDTO)));
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResponseEntity delete(@PathVariable("id") Long id){
        educationService.delete(id);
        return response();
    }
}
