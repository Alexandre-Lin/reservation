package com.project.reservation.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ReservationArgumentMethodNotValidException extends RuntimeException
{
	private String message;
	
	public ReservationArgumentMethodNotValidException(String message)
	{
		super(message);
		this.message = message;
	}
}
