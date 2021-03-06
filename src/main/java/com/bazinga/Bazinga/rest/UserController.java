package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.error.ErrorMessage;
import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.rest.dto.education.RequestEducationDTO;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.experience.RequestExperienceDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserResponseDTO;
import com.bazinga.Bazinga.rest.dto.user.UserEducationRequestDTO;
import com.bazinga.Bazinga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity registerUser(@Valid @RequestBody RegisterUserDTO userRequestDTO) {

        try {
            return ResponseEntity.ok().body(userService.register(userRequestDTO));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/candidate/profile",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity getCandidateProfile(){

        try {
            return ResponseEntity.ok(userService.getCandidateProfile());
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/candidate/skills")
    private ResponseEntity addSkillsToUser(@RequestBody List<String> skills) throws OfferException, UserException {
        try {
            return ResponseEntity.ok(userService.addSkillsToUSer(skills));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }

    }

    @RequestMapping(value = "/candidate/experiences", method = RequestMethod.POST)
    private ResponseEntity addExperiences(@RequestBody List<RequestExperienceDTO> experiences){
        try {
            return response(userService.addExperiences(experiences));
        } catch (UserException e) {
            return responseBad(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/candidate/education", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity registerUser(@Valid @RequestBody UserEducationRequestDTO requestDTO) {
        try {
            return ResponseEntity.ok().body(userService.addEducation(requestDTO));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/candidate/education", method = RequestMethod.PUT)
    private ResponseEntity updateEducation(@RequestBody RequestEducationDTO requestEducationDTO){
        try {
            return response(userService.updateEducation(requestEducationDTO));
        } catch (UserException e) {
            return responseBad(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/candidate/locations", method = RequestMethod.POST)
    private ResponseEntity addLocations(@RequestBody List<String> locations) {
        try {
            return response(userService.addLocations(locations));
        } catch (UserException e) {
            return responseBad(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/company/profile")
    private ResponseEntity getCompanyProfile(){
        try {
            return response(userService.getCompanyProfile());
        } catch (UserException e) {
            return responseBad(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

}