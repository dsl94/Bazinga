package com.bazinga.Bazinga.repository;

import com.bazinga.Bazinga.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByActive(boolean active);
}
