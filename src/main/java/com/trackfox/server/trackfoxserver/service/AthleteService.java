package com.trackfox.server.trackfoxserver.service;

import com.trackfox.server.trackfoxserver.dto.AthleteCreateRequest;
import com.trackfox.server.trackfoxserver.dto.AthleteResponse;
import com.trackfox.server.trackfoxserver.exception.AthleteNotFoundException;
import com.trackfox.server.trackfoxserver.exception.UserNotFoundException;
import com.trackfox.server.trackfoxserver.mapper.AthleteMapper;
import com.trackfox.server.trackfoxserver.repository.AthleteRepository;
import com.trackfox.server.trackfoxserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AthleteService {
	private AthleteRepository athleteRepository;
	private UserRepository userRepository;
	private AthleteMapper athleteMapper;

	public AthleteResponse getAthleteProfile(long userId) {
		return athleteMapper.
			toDto(athleteRepository
				.findByUserId(userId)
				.orElseThrow(() -> new AthleteNotFoundException("athlete profile of user '" + userId + "' not found."))
		);
	}

	public AthleteResponse createAthleteProfile(AthleteCreateRequest request, long userId) {
		return athleteMapper
				.toDto(athleteRepository.save(
						athleteMapper.toEntity(request,
								userRepository
										.findById(userId)
										.orElseThrow(()
												->  new UserNotFoundException("user '" + userId + "' not found.")
										)
						)
				)
		);
	}
}