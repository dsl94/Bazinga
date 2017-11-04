package com.bazinga.Bazinga.match;

import com.bazinga.Bazinga.model.Experience;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

@Component
public class Utilities {

    public Double getYearsOfExperience(List<Experience> experiences) {
        int months = 0;
        for (Experience experience : experiences) {
            LocalDate start = experience.getStartDate();
            LocalDate end = experience.getEndDate();
            if (end == null) {
                end = LocalDate.now();
            }

            months += MONTHS.between(start, end);
        }

        return months / 12.0;
    }
}
