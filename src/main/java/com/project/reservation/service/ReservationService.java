package com.project.reservation.service;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.exception.ReservationAlreadyExistsException;
import com.project.reservation.exception.ReservationArgumentMethodNotValidException;
import com.project.reservation.exception.ReservationTypeNotFoundException;

import java.util.List;


public interface ReservationService
{
	/**
	 * To get all reservations
	 *
	 * @return the list with all the reservations
	 */
	List<ReservationConsulterDto> getAllReservations();
	
	/**
	 * To offer the best reservation propositions possible according to a given filter,
	 * <p>
	 * the criteria for offering the best reservations possible are in the following order:
	 * - Number of people
	 * - Type
	 * - Available resources
	 * - Planning
	 * <p>
	 * P.S.: due to COVID reasons, room will not be available one hour before a reservation,
	 * capacity of a room will be only at 70% of its maximum capacity,
	 * rooms can be available from 8am to 8pm all the week except weekend
	 *
	 * @param reservationFilterDto the filter to use
	 *
	 * @return a list of proposition (the first one is the best one)
	 */
	List<ReservationPropositionDto> getPropositions(ReservationFilterDto reservationFilterDto) throws ReservationArgumentMethodNotValidException,
			ReservationTypeNotFoundException;
	
	/**
	 * To save a reservation
	 *
	 * @param reservationPropositionDto the reservation to save
	 *
	 * @return a confirmation boolean
	 */
	Boolean saveReservation(ReservationPropositionDto reservationPropositionDto) throws ReservationAlreadyExistsException,
			ReservationArgumentMethodNotValidException, ReservationTypeNotFoundException;
}
