package com.trackfox.server.trackfoxserver.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "athletes")
@Getter
@Setter
@NoArgsConstructor
public class Athlete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false)
	private int restHR;

	public Athlete(User user, Gender gender, int restHR) {
		this.user = user;
		this.gender = gender;
		this.restHR = restHR;
	}
}