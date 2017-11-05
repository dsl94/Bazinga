package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DjordjeZiga on 04/11/2017.
 */
@Component
@Qualifier("locationMatcher")
public class LocationMatcher implements Matcher {
    @Override
    public boolean match(User user, Offer offer) {
        if (user.getLocations()==null){
            return false;
        }
        List<String> offerLocations= Arrays.asList(offer.getLocations().split(","));
        for (String offerLocation:offerLocations){
            if (user.getLocations().toLowerCase().contains(offerLocation.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
