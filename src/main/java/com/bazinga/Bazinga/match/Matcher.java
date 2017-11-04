package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.User;

public interface Matcher {

    boolean match(User user, Offer offer);
}
