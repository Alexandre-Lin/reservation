package com.project.reservation.dto;

import com.project.reservation.enums.ReservationType;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
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

