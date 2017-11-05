package com.bazinga.Bazinga.service;

import com.bazinga.Bazinga.model.Match;
import com.bazinga.Bazinga.repository.MatchRepository;
import com.bazinga.Bazinga.rest.dto.MatchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
public interface MatchService {

    List<MatchResponseDTO> getMatches(Long offerId);


}
