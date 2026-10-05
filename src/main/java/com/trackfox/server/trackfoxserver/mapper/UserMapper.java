package com.trackfox.server.trackfoxserver.mapper;


import com.trackfox.server.trackfoxserver.dto.AuthResponse;
import com.trackfox.server.trackfoxserver.dto.RegistrationRequest;
import com.trackfox.server.trackfoxserver.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	public User fromDTO(RegistrationRequest request, String hash) {
		return new User(
				request.email(),
				request.name(),
				hash
		);
	}

	public AuthResponse toDTO(User user, String accessToken, String refreshToken) {
		return new AuthResponse(
				user.getId(),
				user.getName(),
				accessToken,
				refreshToken
		);
	}
}