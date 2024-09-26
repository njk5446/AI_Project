package com.ai.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserVitalSign {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@Column(nullable = false)
	private LocalDate workDate;
	
	@Column(length = 8, nullable = false)
	private String userCode;

	@Column(nullable = false)
	private double heartbeat;
	
	@Column(nullable = false)
	private double temperature;
	
	@Column(nullable = false)
	private double outsideTemperature;
	
	@Column(nullable = false)
	private double latitude;
	
	@Column(nullable = false)
	private double longitude;
	
	@Column(nullable = false)
	private LocalDateTime vitalDate; 
	
	
}
