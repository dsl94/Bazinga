package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.error.ErrorMessage;
import com.bazinga.Bazinga.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
@RestController
@RequestMapping("/api/company/matcher")
public class MatchController extends BaseController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/{offerId}",method = RequestMethod.GET)
    public ResponseEntity getMatches(@PathVariable("offerId") Long offerId){
        try {
            return ResponseEntity.ok().body(matchService.getMatches(offerId));
        } catch (Exception e) {
            return responseNoContent();
        }
    }
}
