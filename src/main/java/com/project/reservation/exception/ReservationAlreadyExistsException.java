package com.project.reservation.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ReservationAlreadyExistsException extends RuntimeException
{
	private String message;
	
	public ReservationAlreadyExistsException(String message)
	{
		super(message);
		this.message = message;
	}
}
