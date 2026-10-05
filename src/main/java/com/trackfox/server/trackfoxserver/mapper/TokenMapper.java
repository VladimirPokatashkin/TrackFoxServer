package com.trackfox.server.trackfoxserver.mapper;

import com.trackfox.server.trackfoxserver.dto.RefreshTokensResponse;
import com.trackfox.server.trackfoxserver.entity.Token;
import com.trackfox.server.trackfoxserver.entity.User;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public abstract class TokenMapper {
	public RefreshTokensResponse toDTO(String refreshToken, String accessToken) {
		return new RefreshTokensResponse(
				refreshToken,
				accessToken
		);
	}

	public Token toEntity(User user, String hash, Instant createdAt, Instant expiresAt) {
		return new Token(
				user,
				hash,
				createdAt,
				expiresAt
		);
	}
}