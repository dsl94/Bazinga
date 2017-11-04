package com.bazinga.Bazinga.rest;

import com.bazinga.Bazinga.error.ErrorMessage;
import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferRequestDTO;
import com.bazinga.Bazinga.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/offer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOffer(@Valid @RequestBody CreateOfferRequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(offerService.createOffer(requestDTO));
        } catch (OfferException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }

    @RequestMapping(value = "/offer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getComapniesOffers() {
        try {
            return ResponseEntity.ok(offerService.getCompaniesOffers());
        } catch (OfferException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getErrorCode(), e.getErrorMessage()));
        }
    }
}
