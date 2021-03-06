package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.match.model.SkillMatcherResponse;
import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lazap on 11/4/2017.
 */
@Component
public class SkillMatcher {

    public SkillMatcherResponse match(User user, Offer offer){
        int offerSkillNumber = offer.getSkills().size();
        Set<Skill> offerSkills = offer.getSkills();
        Set<Skill> matchedSkills = new HashSet<>();

        for(Skill userSkill : user.getUserSkills()){
            if(offerSkills.contains(userSkill)){
                matchedSkills.add(userSkill);
            }
        }

        SkillMatcherResponse skillMatcherResponse = new SkillMatcherResponse();
        skillMatcherResponse.setMatchedSkills(matchedSkills);
        skillMatcherResponse.setMatchedPercentage(((double) 100/offerSkillNumber  * matchedSkills.size()));

        return skillMatcherResponse;
    }
}
