package com.project.reservation.service;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;

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
	 * To offer the best reservation propositions possible according to a given filter
	 *
	 * @param reservationFilterDto the filter to use
	 *
	 * @return a list of proposition (the first one is the best one)
	 */
	List<ReservationPropositionDto> getPropositions(ReservationFilterDto reservationFilterDto);
	
	/**
	 * To save a reservation
	 *
	 * @param reservationPropositionDto the reservation to save
	 *
	 * @return a confirmation boolean
	 */
	Boolean saveReservation(ReservationPropositionDto reservationPropositionDto);
}
