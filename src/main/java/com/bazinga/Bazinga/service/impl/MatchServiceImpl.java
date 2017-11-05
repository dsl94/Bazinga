package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.model.Match;
import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.repository.MatchRepository;
import com.bazinga.Bazinga.repository.OfferRepository;
import com.bazinga.Bazinga.rest.dto.MatchResponseDTO;
import com.bazinga.Bazinga.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override

    public List<MatchResponseDTO> getMatches(Long offerId) {
        Offer offer=offerRepository.findOne(offerId);
        List<MatchResponseDTO> responseDTOS = new ArrayList<>();

        for(Match match : matchRepository.findByOffer(offer)) {
            MatchResponseDTO responseDTO = new MatchResponseDTO();
            responseDTO.setPercentage(match.getSkillPercentage());
            responseDTO.setSkills(match.getMatchedSkills());
            responseDTO.setUserName(match.getUser().getName());
            responseDTOS.add(responseDTO);
        }

        return responseDTOS;
    }
}
