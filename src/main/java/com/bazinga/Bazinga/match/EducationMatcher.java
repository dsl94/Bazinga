package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.model.Education;
import com.bazinga.Bazinga.model.EducationLevel;
import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "educationMatcher")
public class EducationMatcher implements Matcher {

    @Override
    public boolean match(User user, Offer offer) {
        EducationLevel minimum = offer.getMinimumLevel();

        Education education = user.getEducation();
        EducationLevel usersLevel = education.getLevel();


        if (usersLevel.equals(EducationLevel.BSC)) {
            return minimum.equals(EducationLevel.BSC);
        } else if (usersLevel.equals(EducationLevel.MSC)) {
            return minimum.equals(EducationLevel.BSC) || minimum.equals(EducationLevel.MSC);
        } else
            return usersLevel.equals(EducationLevel.PHD) || usersLevel.equals(EducationLevel.OTHER) && minimum.equals(EducationLevel.OTHER);
    }
}
