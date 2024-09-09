package com.crickfever.repositories;

import com.crickfever.entities.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepo extends JpaRepository<Matches, Integer> {
    Optional<Matches> findByMatchName(String matchName);
}
