package com.trackfox.server.trackfoxserver.controller;


import com.trackfox.server.trackfoxserver.dto.TrainingDTO;
import com.trackfox.server.trackfoxserver.exception.UserNotFoundException;
import com.trackfox.server.trackfoxserver.service.TokenService;
import com.trackfox.server.trackfoxserver.service.TrainingService;
import com.trackfox.server.trackfoxserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
@AllArgsConstructor
public class TrainingController {
	private TrainingService trainingService;


	@PostMapping("save")
	public TrainingDTO saveTraining(
			@RequestBody TrainingDTO dto,
			@AuthenticationPrincipal long userId
	) {
		return trainingService.saveTraining(dto, userId);
	}

	@GetMapping("get")
	public List<TrainingDTO> getAllTrainings(@AuthenticationPrincipal long userId) {
		return trainingService.getAllTrainingsOfUser(userId);
	}
}