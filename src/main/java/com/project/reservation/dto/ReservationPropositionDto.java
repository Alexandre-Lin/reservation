package com.project.reservation.dto;
import java.time.LocalDateTime;


public class ReservationPropositionDto
{
	private String type;
	
	private LocalDateTime startHour;
	
	private LocalDateTime endHour;
	
	private Integer peopleNumber;
	
	private Integer roomId;
}
