package com.trackfox.server.trackfoxserver.controller;

import com.trackfox.server.trackfoxserver.dto.AthleteCreateRequest;
import com.trackfox.server.trackfoxserver.dto.AthleteResponse;
import com.trackfox.server.trackfoxserver.exception.UserNotFoundException;
import com.trackfox.server.trackfoxserver.service.AthleteService;
import com.trackfox.server.trackfoxserver.service.TokenService;
import com.trackfox.server.trackfoxserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athlete")
@AllArgsConstructor
public class AthleteController {
	private AthleteService athleteService;
	private TokenService tokenService;
	private UserService userService;

	public AthleteResponse getAthleteProfile(@RequestParam String accessToken) {
		long userId = tokenService.getUserIdFromToken(accessToken);
		return athleteService.getAthleteProfile(userId);
	}

	@PostMapping("create")
	public AthleteResponse createAthleteProfile(@RequestBody AthleteCreateRequest request, @RequestParam String accessToken) {
		long userId = tokenService.getUserIdFromToken(accessToken);
		return athleteService.createAthleteProfile(
				request,
				userService
						.getUserById(userId)
						.orElseThrow(() -> new UserNotFoundException("user '" + userId + "' not found."))
		);
	}
}