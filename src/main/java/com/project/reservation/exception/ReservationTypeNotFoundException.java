package com.project.reservation.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ReservationTypeNotFoundException extends RuntimeException
{
	private String message;
	
	public ReservationTypeNotFoundException(String message)
	{
		super(message);
		this.message = message;
	}
}
