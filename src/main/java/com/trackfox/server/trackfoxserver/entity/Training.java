package com.trackfox.server.trackfoxserver.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@NoArgsConstructor
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	@CreationTimestamp
	private Instant time;

	@Column(nullable = false)
	private int duration;

	@Column(nullable = false)
	private int averageHR;

	@Column(nullable = false)
	private int maxHR;


	public Training(User user, Instant time, int duration, int averageHR, int maxHR) {
		this.user = user;
		this.time = time;
		this.duration = duration;
		this.averageHR = averageHR;
		this.maxHR = maxHR;
	}
}