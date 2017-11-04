package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferRequestDTO;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferResponseDTO;

public interface OfferService {

    CreateOfferResponseDTO createOffer(CreateOfferRequestDTO request) throws OfferException;
}
