package com.project.reservation.controller.impl;

import com.project.reservation.controller.ReservationController;
import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "APIs for reservation")
public class ReservationControllerImpl implements ReservationController
{
	
	@Autowired
	private ReservationService reservationService;
	
	@Override
	@Operation(summary = "Get planning", description = "Get all reservations", tags = "Reservation API")
	@GetMapping(value = "/all")
	public ResponseEntity<List<ReservationConsulterDto>> getReservations()
	{
		return ResponseEntity.ok(this.reservationService.getAllReservations());
	}
	
	@Override
	@Operation(summary = "Get propositions",
			   description = "Get the best propositions possible according to a given filter",
			   tags = "Reservation API")
	@PostMapping(value = "/propositions")
	public ResponseEntity<List<ReservationPropositionDto>> getPropositions(ReservationFilterDto reservationFilterDto)
	{
		if (reservationFilterDto == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(this.reservationService.getPropositions(reservationFilterDto));
	}
	
	@Override
	@Operation(summary = "Save reservation", description = "Save a wanted reservation", tags = "Reservation API")
	@PostMapping(value = "/book")
	public ResponseEntity<Boolean> getDesiredProposition(ReservationPropositionDto reservationPropositionDto)
	{
		if (reservationPropositionDto == null)
			return ResponseEntity.badRequest().build();
		Boolean isOk = this.reservationService.saveReservation(reservationPropositionDto);
		if (isOk)
			return ResponseEntity.ok(true);
		return ResponseEntity.ok(false);
	}
}
