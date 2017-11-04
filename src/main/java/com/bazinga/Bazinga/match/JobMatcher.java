package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.match.model.SkillMatcherResponse;
import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.repository.OfferRepository;
import com.bazinga.Bazinga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Scheduled(fixedRate = 60000)
    public void doMatch() {
        // We have to do matching for every user and for every offer for every user
        // So first we are starting users
        List<User> users = userRepository.findAll();
        List<Offer> offers = offerRepository.findAllByActive(true);

        for (Offer offer : offers) {
            for (User user : users) {
                if (matchersMatched(user, offer)) {
                    // Now we check skills matching
                    SkillMatcherResponse skillMatcherResponse = skillMatcher.match(user, offer);
                    if (skillMatcherResponse.getMatchedPercentage() >= 30) {
                        // This is complete match and we can save it in DB
                    }
                }
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
