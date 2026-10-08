package com.trackfox.server.trackfoxserver.repository;


import com.trackfox.server.trackfoxserver.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
	Optional<Athlete> findByUserId(Long userId);
	boolean existsByUserId(Long userId);
}