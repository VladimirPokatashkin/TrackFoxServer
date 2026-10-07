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
	private UserService userService;


	@PostMapping("save")
	public TrainingDTO saveTraining(
			@RequestBody TrainingDTO dto,
			@AuthenticationPrincipal long userId
	) {
		return trainingService.saveTraining(dto,
				userService
						.getUserById(userId)
						.orElseThrow(() -> new UserNotFoundException("user not found"))
		);
	}

	@GetMapping("get")
	public List<TrainingDTO> getAllTrainings(@AuthenticationPrincipal long userId) {
		return trainingService.getAllTrainingsOfUser(
				userService
						.getUserById(userId)
						.orElseThrow(() -> new UserNotFoundException("user not found."))
		);
	}
}