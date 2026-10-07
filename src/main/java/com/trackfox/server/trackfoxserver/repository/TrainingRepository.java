package com.trackfox.server.trackfoxserver.repository;


import com.trackfox.server.trackfoxserver.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
	public List<Training> findAllByUserId(Long userId);
}