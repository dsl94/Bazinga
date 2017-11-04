package com.bazinga.Bazinga.util;

import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferRequestDTO;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class OfferMapper {

    public Offer fromRequest(CreateOfferRequestDTO request){
        Offer forSave = new Offer();
        forSave.setTitle(request.getTitle());
        forSave.setActive(true);
        forSave.setMinimumLevel(request.getMinimumLevel());
        String locations = StringUtils.join(request.getLocations(), ",");
        forSave.setLocations(locations);
        forSave.setDescription(request.getDescription());
        forSave.setMinYearsOfExperience(request.getMinYearsOfExperience());

        return forSave;
    }

    public CreateOfferResponseDTO fromEntity(Offer result){
        CreateOfferResponseDTO response = new CreateOfferResponseDTO();
        response.setOfferId(result.getId());
        response.setTitle(result.getTitle());
        response.setActive(result.getActive());
        response.setDescription(result.getDescription());
        response.setLocations(Arrays.asList(result.getLocations().split("\\s*,\\s*")));
        response.setMinimumEducation(result.getMinimumLevel());
        response.setSkills(new ArrayList<>(result.getSkills()));
        response.setUserId(result.getUser().getId());
        response.setMinYearsOfExperience(result.getMinYearsOfExperience());

        return response;
    }
}
