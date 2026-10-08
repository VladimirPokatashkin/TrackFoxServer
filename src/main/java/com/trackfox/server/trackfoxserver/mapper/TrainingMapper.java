package com.trackfox.server.trackfoxserver.mapper;

import com.trackfox.server.trackfoxserver.dto.TrainingDTO;
import com.trackfox.server.trackfoxserver.entity.Training;
import com.trackfox.server.trackfoxserver.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TrainingMapper {
	public TrainingDTO toDTO(Training training) {
		return new TrainingDTO(
				training.getUser().getId(),
				training.getTime(),
				training.getDuration(),
				training.getAverageHR(),
				training.getMaxHR()
		);
	}

	public Training toEntity(TrainingDTO dto, User user) {
		return new Training(
				user,
				dto.time(),
				dto.duration(),
				dto.averageHR(),
				dto.maxHR()
		);
	}
}