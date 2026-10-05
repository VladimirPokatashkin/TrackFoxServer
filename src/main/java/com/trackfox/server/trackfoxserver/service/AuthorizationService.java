package com.trackfox.server.trackfoxserver.service;

import com.trackfox.server.trackfoxserver.dto.AuthResponse;
import com.trackfox.server.trackfoxserver.dto.LoginRequest;
import com.trackfox.server.trackfoxserver.dto.RefreshTokensResponse;
import com.trackfox.server.trackfoxserver.dto.RegistrationRequest;
import com.trackfox.server.trackfoxserver.entity.Token;
import com.trackfox.server.trackfoxserver.entity.User;
import com.trackfox.server.trackfoxserver.exception.InvalidTokenException;
import com.trackfox.server.trackfoxserver.exception.UserExistsException;
import com.trackfox.server.trackfoxserver.exception.UserNotFoundException;
import com.trackfox.server.trackfoxserver.exception.WrongPasswordException;
import com.trackfox.server.trackfoxserver.repository.TokenRepository;
import com.trackfox.server.trackfoxserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthorizationService {
	private UserRepository userRepository;
	private TokenRepository tokenRepository;
	private TokenService tokenService;

	public AuthResponse login(LoginRequest request) {
		var user = userRepository
				.findByName(request.name())
				.orElseThrow(() -> new UserNotFoundException("user '" + request.name() + "' not found."));

		if (!BCrypt.checkpw(request.password(), user.getPasswordHash())) {
			throw new WrongPasswordException("wrong password.");
		}

		String accessToken = tokenService.generateAccessToken(user.getId());
		String refreshToken = tokenService.generateRefreshToken();

		tokenRepository.save(new Token(
				user,
				sha256(refreshToken),
				Instant.now(),
				Instant.now().plus(60, ChronoUnit.DAYS)
		));

		return new AuthResponse(
				user.getId(),
				user.getName(),
				accessToken,
				refreshToken
		);
	}

	public AuthResponse register(RegistrationRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new UserExistsException("email '" + request.email() + "' already registered.");
		}

		var hash = BCrypt.hashpw(request.password(), BCrypt.gensalt());

		var user = userRepository.save(new User(
				request.email(),
				request.name(),
				hash
		));

		String accessToken = tokenService.generateAccessToken(user.getId());
		String refreshToken = tokenService.generateRefreshToken();

		tokenRepository.save(new Token(
				user,
				sha256(refreshToken),
				Instant.now(),
				Instant.now().plus(60, ChronoUnit.DAYS)
		));

		return new AuthResponse(
				user.getId(),
				user.getName(),
				accessToken,
				refreshToken
		);
	}

	public RefreshTokensResponse refreshTokens(String refreshToken) {
		var token = tokenRepository.findByHash(sha256(refreshToken));

		if (token.isEmpty() || token.get().isExpired()) {
			throw new InvalidTokenException("invalid token.");
		}

		String accessToken = tokenService.generateAccessToken(token.get().getUser().getId());
		refreshToken = tokenService.generateRefreshToken();

		tokenRepository.save(new Token(
				token.get().getUser(),
				sha256(refreshToken),
				Instant.now(),
				Instant.now().plus(60, ChronoUnit.DAYS)
		));

		return new RefreshTokensResponse(refreshToken,  accessToken);
	}


	private String sha256(String input) {
		try {
			return Arrays.toString(
					MessageDigest
							.getInstance("SHA-256")
							.digest(input.getBytes(StandardCharsets.UTF_8))
			);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}