package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.error.ErrorMessage;
import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.error.UserException;
import com.bazinga.Bazinga.rest.dto.user.CandidateProfileDTO;
import com.bazinga.Bazinga.rest.dto.user.RegisterUserDTO;
import com.bazinga.Bazinga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

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

    @RequestMapping(value = "/candidate/profile")
    private ResponseEntity getCandidateProfile(@PathParam(value = "id") Long id){

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
}