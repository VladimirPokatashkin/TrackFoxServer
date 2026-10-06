package com.trackfox.server.trackfoxserver.mapper;


import com.trackfox.server.trackfoxserver.dto.AthleteCreateRequest;
import com.trackfox.server.trackfoxserver.dto.AthleteResponse;
import com.trackfox.server.trackfoxserver.entity.Athlete;
import com.trackfox.server.trackfoxserver.entity.Gender;
import com.trackfox.server.trackfoxserver.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AthleteMapper {
	public AthleteResponse toDto(Athlete athlete) {
		return new AthleteResponse(
				athlete.getId(),
				athlete.getUser().getId(),
				athlete.getGender().toString(),
				athlete.getRestHR()
		);
	}

	public Athlete toEntity(AthleteCreateRequest request, User user) {
		return new Athlete(
				user,
				Gender.valueOf(request.gender()),
				request.restHR()
		);
	}
}