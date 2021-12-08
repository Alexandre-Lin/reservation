package com.project.reservation.dto;
import com.project.reservation.enums.ReservationType;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReservationPropositionDto
{
	private ReservationType type;
	
	private LocalDateTime startHour;
	
	private LocalDateTime endHour;
	
	private Integer peopleNumber;
	
	private Integer roomId;
	
	private String roomName;
	
	private Boolean useExtraEcran;
	
	private Boolean useExtraPieuvre;
	
	private Boolean useExtraTableau;
	
	private Boolean useExtraWebcam;
	
	public Boolean fieldsAreNotNull()
	{
		return this.type != null &&
				this.startHour != null &&
				this.endHour != null &&
				this.peopleNumber != null &&
				this.roomId != null &&
				this.roomName != null &&
				this.useExtraEcran != null &&
				this.useExtraPieuvre != null &&
				this.useExtraTableau != null &&
				this.useExtraWebcam != null;
	}
}
