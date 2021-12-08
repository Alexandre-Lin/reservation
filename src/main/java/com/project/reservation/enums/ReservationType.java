package com.project.reservation.enums;

import lombok.Getter;


public enum ReservationType
{
	// need a "ecran" (screen), a "pieuvre" and a webcam
	VC("VC"),
	// need a "tableau" (board)
	SPEC("SPEC"),
	// only need a room
	RS("RS"),
	// need a "tableau" (board), a "ecran" (screen) and a "pieuvre"
	RC("RC");
	
	public static boolean isValidType(String type)
	{
		for (ReservationType reservationType : ReservationType.values())
		{
			if (reservationType.name().equals(type))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Getter
	private String type;
	
	ReservationType(String type)
	{
		this.type = type;
	}
	
}
