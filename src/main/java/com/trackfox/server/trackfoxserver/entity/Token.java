package com.trackfox.server.trackfoxserver.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false, unique = true)
	private String hash;

	@Column(nullable = false)
	@CreationTimestamp
	private Instant createdAt;

	@Column(nullable = false)
	private Instant expiresAt;


	public Token(User user, String hash,  Instant createdAt, Instant expiresAt) {
		this.user = user;
		this.hash = hash;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}

	@Transient
	public boolean isExpired() {
		return expiresAt.isBefore(Instant.now());
	}
}