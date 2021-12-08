package com.project.reservation.controller.impl;

import com.project.reservation.controller.ReservationController;
import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.exception.ReservationAlreadyExistsException;
import com.project.reservation.exception.ReservationArgumentMethodNotValidException;
import com.project.reservation.exception.ReservationTypeNotFoundException;
import com.project.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "APIs for reservation")
public class ReservationControllerImpl implements ReservationController
{
	
	@Autowired
	private ReservationService reservationService;
	
	// exception management
	@ExceptionHandler(value = ReservationTypeNotFoundException.class)
	public ResponseEntity<String> handleReservationTypeNotFoundException(ReservationTypeNotFoundException reservationTypeNotFoundException)
	{
		return ResponseEntity.badRequest().body("The reservation type is not valid");
	}
	
	@ExceptionHandler(value = ReservationArgumentMethodNotValidException.class)
	public ResponseEntity<String> handleReservationArgumentMethodNotValidException(ReservationArgumentMethodNotValidException reservationArgumentMethodNotValidException)
	{
		return ResponseEntity.badRequest().body("All the fields of the body must not be null");
	}
	
	@ExceptionHandler(value = ReservationAlreadyExistsException.class)
	public ResponseEntity<String> handleReservationAlreadyExistsException(ReservationAlreadyExistsException reservationAlreadyExistsException)
	{
		return ResponseEntity.badRequest().body("Reservation already taken !");
	}
	
	// APIS
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
	public ResponseEntity<List<ReservationPropositionDto>> getPropositions(ReservationFilterDto reservationFilterDto) throws ReservationArgumentMethodNotValidException,
			ReservationTypeNotFoundException
	{
		if (reservationFilterDto == null)
			throw new ReservationArgumentMethodNotValidException("Body is empty");
		return ResponseEntity.ok(this.reservationService.getPropositions(reservationFilterDto));
	}
	
	@Override
	@Operation(summary = "Save reservation", description = "Save a wanted reservation", tags = "Reservation API")
	@PostMapping(value = "/book")
	public ResponseEntity<Boolean> getDesiredProposition(ReservationPropositionDto reservationPropositionDto) throws ReservationAlreadyExistsException,
			ReservationArgumentMethodNotValidException, ReservationTypeNotFoundException
	{
		if (reservationPropositionDto == null)
			throw new ReservationArgumentMethodNotValidException("Body is empty");
		Boolean isOk = this.reservationService.saveReservation(reservationPropositionDto);
		if (isOk)
			return ResponseEntity.ok(true);
		return ResponseEntity.badRequest().build();
	}
}
