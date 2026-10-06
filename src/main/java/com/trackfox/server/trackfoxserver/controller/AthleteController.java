package com.trackfox.server.trackfoxserver.controller;

import com.trackfox.server.trackfoxserver.dto.AthleteCreateRequest;
import com.trackfox.server.trackfoxserver.dto.AthleteResponse;
import com.trackfox.server.trackfoxserver.service.AthleteService;
import com.trackfox.server.trackfoxserver.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/athlete")
@AllArgsConstructor
public class AthleteController {
	private AthleteService athleteService;
	private TokenService tokenService;

	public AthleteResponse getAthleteProfile(@RequestParam String accessToken) {
		long userId = tokenService.getUserIdFromToken(accessToken);
		return athleteService.getAthleteProfile(userId);
	}

	public AthleteResponse createAthleteProfile(@RequestBody AthleteCreateRequest request, @RequestParam String accessToken) {
		long userId = tokenService.getUserIdFromToken(accessToken);
		return athleteService.createAthleteProfile(request, userId);
	}
}