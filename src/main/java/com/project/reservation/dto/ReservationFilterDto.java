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
public class ReservationFilterDto
{
	private ReservationType type;
	
	private LocalDateTime startHour;
	
	private LocalDateTime endHour;
	
	private Integer peopleNumber;
	
	private Boolean needEcran;
	
	private Boolean needPieuvre;
	
	private Boolean needTableau;
	
	private Boolean needWebcam;
}
