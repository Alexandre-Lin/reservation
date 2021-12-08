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
public class ReservationPropositionDto
{
	private ReservationType type;
	
	private LocalDateTime startHour;
	
	private LocalDateTime endHour;
	
	private Integer peopleNumber;
	
	private Integer roomId;
	
	public Boolean fieldsAreNotNull()
	{
		return this.type != null &&
				this.startHour != null &&
				this.endHour != null &&
				this.peopleNumber != null &&
				this.roomId != null;
	}
}
