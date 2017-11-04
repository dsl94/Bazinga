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
import com.bazinga.Bazinga.util.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private OfferMapper mapper;

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

        Offer forSave = mapper.fromRequest(request);
        forSave.setUser(user);

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

        return mapper.fromEntity(result);
    }

    @Override
    public CreateOfferResponseDTO updateOffer(CreateOfferRequestDTO request, Long id) throws OfferException {
        Offer offer = offerRepository.findOne(id);
        if (offer == null) {
            throw new OfferException(ErrorCode.OFFER_NOT_FOUND, "Offer with id not found");
        }

        Offer newOffer = mapper.fromRequest(request);
        newOffer.setId(offer.getId());
        newOffer.setUser(offer.getUser());

        Offer result = offerRepository.save(newOffer);

        return mapper.fromEntity(result);
    }

    @Override
    public void flag(Long id) throws OfferException {
        Offer offer = offerRepository.findOne(id);
        if (offer == null) {
            throw new OfferException(ErrorCode.OFFER_NOT_FOUND, "Offer with that ID not found");
        }

        if (!offer.getActive()) {
            throw new OfferException(ErrorCode.OFFER_IS_ALREADY_INACTIVE, "Offer is already inactive");
        }

        offer.setActive(false);
        offerRepository.save(offer);
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

        List<CreateOfferResponseDTO> response;

        List<Offer> offers = user.getOffers();
        response = offers.stream().map(offer -> mapper.fromEntity(offer)).collect(Collectors.toList());

        return response;
    }
}
