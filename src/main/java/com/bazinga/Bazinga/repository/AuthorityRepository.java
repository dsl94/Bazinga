package com.bazinga.Bazinga.repository;

import com.bazinga.Bazinga.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
