package com.trackfox.server.trackfoxserver.controller;


import com.trackfox.server.trackfoxserver.dto.*;
import com.trackfox.server.trackfoxserver.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthorizationController {
	private AuthorizationService authorizationService;

	@GetMapping("refresh")
	public RefreshTokensResponse refreshToken(@RequestBody RefreshTokenRequest request) {
		return authorizationService.refreshTokens(request);
	}

	@PostMapping("register")
	public AuthResponse register(@RequestBody RegistrationRequest request) {
		return authorizationService.register(request);
	}

	@PostMapping("login")
	public AuthResponse login(@RequestBody LoginRequest request) {
		return authorizationService.login(request);
	}
}