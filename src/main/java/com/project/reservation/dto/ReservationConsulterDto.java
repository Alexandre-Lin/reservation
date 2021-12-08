package com.project.reservation.dto;

import com.project.reservation.enums.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationConsulterDto
{
	private ReservationType type;
	
	private LocalDateTime startHour;
	
	private LocalDateTime endHour;
	
	private Integer peopleNumber;
	
	private String roomName;
}

