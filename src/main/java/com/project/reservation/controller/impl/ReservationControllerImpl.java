package com.project.reservation.controller.impl;

import com.project.reservation.controller.ReservationController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "APIs for reservation")
public class ReservationControllerImpl implements ReservationController
{
	@Override
	@Operation(summary = "Get planning",description = "Get all reservations")
	public ResponseEntity<Boolean> getReservations()
	{
		return ResponseEntity.ok(true);
	}
}
