package com.trackfox.server.trackfoxserver.service;

import com.trackfox.server.trackfoxserver.dto.TrainingDTO;
import com.trackfox.server.trackfoxserver.exception.UserNotFoundException;
import com.trackfox.server.trackfoxserver.mapper.TrainingMapper;
import com.trackfox.server.trackfoxserver.repository.TrainingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {
	private TrainingRepository trainingRepository;
	private TrainingMapper trainingMapper;
	private UserService userService;

	@Transactional
	public TrainingDTO saveTraining(TrainingDTO dto, long userId) {
		trainingRepository.save(
				trainingMapper.toEntity(dto,
						userService
								.getUserById(userId)
								.orElseThrow(() -> new UserNotFoundException("user '" + userId + "' not found.")))
		);
		return dto;
	}

	public List<TrainingDTO> getAllTrainingsOfUser(long userId) {
		var user = userService
				.getUserById(userId)
				.orElseThrow(() -> new UserNotFoundException("user '" + userId + "' not found.")
		);

		return trainingRepository
				.findAllByUserId(user.getId())
				.stream()
				.map(trainingMapper::toDTO)
				.toList();
	}
}