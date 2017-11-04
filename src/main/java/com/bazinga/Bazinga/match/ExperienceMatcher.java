package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Qualifier(value = "experienceMatcher")
public class ExperienceMatcher implements Matcher{

    @Autowired
    private Utilities utilities;

    @Override
    public boolean match(User user, Offer offer) {
        int required = offer.getMinYearsOfExperience();

        double userExperience = utilities.getYearsOfExperience(new ArrayList<>(user.getExperiences()));

        return userExperience >= required;
    }
}
