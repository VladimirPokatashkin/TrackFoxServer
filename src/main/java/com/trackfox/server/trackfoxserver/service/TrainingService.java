package com.trackfox.server.trackfoxserver.service;

import com.trackfox.server.trackfoxserver.dto.TrainingDTO;
import com.trackfox.server.trackfoxserver.entity.User;
import com.trackfox.server.trackfoxserver.mapper.TrainingMapper;
import com.trackfox.server.trackfoxserver.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {
	private TrainingRepository trainingRepository;
	private TrainingMapper trainingMapper;

	public TrainingDTO saveTraining(TrainingDTO dto, User user) {
		trainingRepository.save(
				trainingMapper.toEntity(dto, user)
		);
		return dto;
	}

	public List<TrainingDTO> getAllTrainingsOfUser(User user) {
		return trainingRepository
				.findAllByUserId(user.getId())
				.stream()
				.map(trainingMapper::toDTO)
				.toList();
	}
}