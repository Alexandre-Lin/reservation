package com.project.reservation.controller;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.exception.ReservationAlreadyExistsException;
import com.project.reservation.exception.ReservationArgumentMethodNotValidException;
import com.project.reservation.exception.ReservationTypeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ReservationController
{
	/**
	 * To get all the reservations stored in db
	 *
	 * @return all the reservations
	 */
	ResponseEntity<List<ReservationConsulterDto>> getReservations();
	
	/**
	 * To give propositions according to a given filter (ex: the user's needs)
	 *
	 * @param reservationFilterDto the filters to use to give the best propositions
	 *
	 * @return a list of propositions (the first proposition will be the best one)
	 */
	ResponseEntity<List<ReservationPropositionDto>> getPropositions(
			@RequestBody
					ReservationFilterDto reservationFilterDto
	) throws ReservationArgumentMethodNotValidException,
			ReservationTypeNotFoundException;
	
	/**
	 * To save a reservation to the db
	 *
	 * @param reservationPropositionDto the desired reservation/proposition to save
	 *
	 * @return a status to confirm that the reservation is saved
	 */
	ResponseEntity<Boolean> getDesiredProposition(
			@RequestBody
					ReservationPropositionDto reservationPropositionDto
	) throws ReservationAlreadyExistsException,
			ReservationArgumentMethodNotValidException, ReservationTypeNotFoundException;
}
