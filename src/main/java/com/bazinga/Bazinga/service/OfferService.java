package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferRequestDTO;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferResponseDTO;

import java.util.List;

public interface OfferService {

    CreateOfferResponseDTO createOffer(CreateOfferRequestDTO request) throws OfferException;
    List<CreateOfferResponseDTO> getCompaniesOffers() throws OfferException;
}
