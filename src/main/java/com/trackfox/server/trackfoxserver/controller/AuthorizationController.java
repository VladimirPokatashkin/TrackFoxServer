package com.trackfox.server.trackfoxserver.controller;


import com.trackfox.server.trackfoxserver.dto.AuthResponse;
import com.trackfox.server.trackfoxserver.dto.LoginRequest;
import com.trackfox.server.trackfoxserver.dto.RefreshTokensResponse;
import com.trackfox.server.trackfoxserver.dto.RegistrationRequest;
import com.trackfox.server.trackfoxserver.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthorizationController {
	private AuthorizationService authorizationService;

	@GetMapping("refresh")
	public RefreshTokensResponse refreshToken(@RequestParam String refreshToken) {
		return authorizationService.refreshTokens(refreshToken);
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