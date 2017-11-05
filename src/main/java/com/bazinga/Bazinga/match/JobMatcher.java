package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.match.model.SkillMatcherResponse;
import com.bazinga.Bazinga.model.*;
import com.bazinga.Bazinga.repository.MatchRepository;
import com.bazinga.Bazinga.repository.OfferRepository;
import com.bazinga.Bazinga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@EnableScheduling
public class JobMatcher {

    @Autowired
    private List<? extends Matcher> matchers;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private SkillMatcher skillMatcher;

    @Autowired
    private MatchRepository matchRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void doMatch() {
        // We have to do matching for every user and for every offer for every user
        // So first we are starting users
        List<User> users = userRepository.findAll();
        List<Offer> offers = offerRepository.findAllByActive(true);
        for (Offer offer : offers) {
            List<Match> matches = new ArrayList<>();
            for (User user : users) {
                if (matchersMatched(user, offer)) {
                    // Now we check skills matching
                    SkillMatcherResponse skillMatcherResponse = skillMatcher.match(user, offer);
                    if (skillMatcherResponse.getMatchedPercentage() >= 30) {
                        Set<MatchedSkills> listMatchedSkills = new HashSet<>();
                        for (Skill skill : skillMatcherResponse.getMatchedSkills()) {
                            MatchedSkills matchedSkills = new MatchedSkills();
                            matchedSkills.setSkill(skill);
                            listMatchedSkills.add(matchedSkills);
                        }
                        Match match = new Match();
                        match.setUser(user);
                        match.setOffer(offer);
                        match.setSkillPercentage(skillMatcherResponse.getMatchedPercentage());
                        match.setMatchedSkills(listMatchedSkills);
                        matches.add(match);
                    }
                }
            }
            matchRepository.deleteByOffer(offer);
            if (matches.size() < 5) {
                matchRepository.save(matches);
            } else {
                Collections.sort(matches);
                matchRepository.save(matches.subList(0, 4));
            }
        }
    }

    private boolean matchersMatched(User user, Offer offer) {
        for (Matcher matcher : matchers) {
            if (!matcher.match(user, offer)) {
                return false;
            }
        }
        return true;
    }
}
