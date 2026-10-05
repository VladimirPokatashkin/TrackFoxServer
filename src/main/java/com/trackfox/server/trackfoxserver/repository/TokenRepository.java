package com.trackfox.server.trackfoxserver.repository;

import com.trackfox.server.trackfoxserver.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
	Optional<Token> findByUserId(Long userId);
	Optional<Token> findByHash(String  hash);
}