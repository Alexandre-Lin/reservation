package com.project.reservation.controller;

import org.springframework.http.ResponseEntity;


public interface ReservationController
{
	ResponseEntity<Boolean> getReservations();
}
