package com.trackfox.server.trackfoxserver.service;


import com.trackfox.server.trackfoxserver.entity.User;
import com.trackfox.server.trackfoxserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository userRepository;

	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}
}