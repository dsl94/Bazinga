package com.bazinga.Bazinga.repository;

import com.bazinga.Bazinga.model.Match;
import com.bazinga.Bazinga.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by DjordjeZiga on 05/11/2017.
 */
public interface MatchRepository extends JpaRepository<Match,Long>{

    void deleteByOffer(Offer offer);
    List<Match> findByOffer(Offer offer);

}
