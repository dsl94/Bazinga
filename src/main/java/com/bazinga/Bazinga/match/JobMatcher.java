package com.bazinga.Bazinga.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobMatcher {

    @Autowired
    private List<? extends Matcher> matchers;
}
