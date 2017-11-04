package com.bazinga.Bazinga.service.impl;

import com.bazinga.Bazinga.error.ErrorCode;
import com.bazinga.Bazinga.error.OfferException;
import com.bazinga.Bazinga.model.Authority;
import com.bazinga.Bazinga.model.Offer;
import com.bazinga.Bazinga.model.Skill;
import com.bazinga.Bazinga.model.User;
import com.bazinga.Bazinga.repository.OfferRepository;
import com.bazinga.Bazinga.repository.SkillRepository;
import com.bazinga.Bazinga.repository.UserRepository;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferRequestDTO;
import com.bazinga.Bazinga.rest.dto.offer.CreateOfferResponseDTO;
import com.bazinga.Bazinga.security.AuthoritiesConstants;
import com.bazinga.Bazinga.service.OfferService;
import com.bazinga.Bazinga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public CreateOfferResponseDTO createOffer(CreateOfferRequestDTO request) throws OfferException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // First do validation
        // Check if user exist
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new OfferException(ErrorCode.USER_NOT_FOUND, "User with that id not found");
        }
        // Check if user is COMPANY
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.COMPANY);
        if (!user.getAuthorities().contains(authority)) {
            throw new OfferException(ErrorCode.USER_IS_NOT_COMPANY, "User is not company and can't create offer");
        }

        Offer forSave = new Offer();
        forSave.setTitle(request.getTitle());
        forSave.setActive(true);
        forSave.setUser(user);
        forSave.setMinimumLevel(request.getMinimumLevel());
        String locations = StringUtils.join(request.getLocations(), ",");
        forSave.setLocations(locations);
        forSave.setDescription(request.getDescription());
        forSave.setMinYearsOfExperience(request.getMinYearsOfExperience());

        // Now map skills
        Set<Skill> skills = new HashSet<>();
        for (String skillName : request.getSkills()) {
            // If skill exist add that skill
            Skill skill = skillRepository.findByNameIgnoreCase(skillName);
            if (skill != null) {
                skills.add(skill);
            } else {
                skill = new Skill();
                skill.setName(skillName);
                skill = skillRepository.save(skill);
                skills.add(skill);
            }
        }

        forSave.setSkills(skills);

        Offer result = offerRepository.save(forSave);

        CreateOfferResponseDTO response = new CreateOfferResponseDTO();
        response.setTitle(result.getTitle());
        response.setActive(result.getActive());
        response.setDescription(result.getDescription());
        response.setLocations(Arrays.asList(result.getLocations().split("\\s*,\\s*")));
        response.setMinimumEducation(result.getMinimumLevel());
        response.setSkills(new ArrayList<>(result.getSkills()));
        response.setUserId(result.getUser().getId());
        response.setMinYearsOfExperience(result.getMinYearsOfExperience());

        return response;
    }

    @Override
    public List<CreateOfferResponseDTO> getCompaniesOffers() throws OfferException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // First do validation
        // Check if user exist
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new OfferException(ErrorCode.USER_NOT_FOUND, "User with that id not found");
        }
        // Check if user is COMPANY
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.COMPANY);
        if (!user.getAuthorities().contains(authority)) {
            throw new OfferException(ErrorCode.USER_IS_NOT_COMPANY, "User is not company");
        }

        List<CreateOfferResponseDTO> response = new ArrayList<>();

        List<Offer> offers = user.getOffers();
        for (Offer offer : offers) {
            CreateOfferResponseDTO offerResponse  = new CreateOfferResponseDTO();
            offerResponse.setTitle(offer.getTitle());
            offerResponse.setActive(offer.getActive());
            offerResponse.setDescription(offer.getDescription());
            offerResponse.setLocations(Arrays.asList(offer.getLocations().split("\\s*,\\s*")));
            offerResponse.setMinimumEducation(offer.getMinimumLevel());
            offerResponse.setSkills(new ArrayList<>(offer.getSkills()));
            offerResponse.setUserId(offer.getUser().getId());
            offerResponse.setMinYearsOfExperience(offer.getMinYearsOfExperience());

            response.add(offerResponse);
        }

        return response;
    }
}
