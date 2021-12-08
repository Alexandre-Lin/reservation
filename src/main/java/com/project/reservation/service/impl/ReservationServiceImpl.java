package com.project.reservation.service.impl;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.entity.Reservation;
import com.project.reservation.entity.Room;
import com.project.reservation.enums.ReservationType;
import com.project.reservation.exception.ReservationAlreadyExistsException;
import com.project.reservation.exception.ReservationArgumentMethodNotValidException;
import com.project.reservation.exception.ReservationTypeNotFoundException;
import com.project.reservation.mapper.ReservationMapper;
import com.project.reservation.repository.ReservationRepository;
import com.project.reservation.service.ReservationService;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.project.reservation.entity.QReservation.reservation;
import static com.project.reservation.entity.QRoom.room;


@Service
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	@Autowired
	protected EntityManager entityManager;
	
	@Override
	@Transactional
	public List<ReservationConsulterDto> getAllReservations()
	{
		return this.reservationMapper.reservationsToReservationConsulterDtos(
				StreamSupport.stream(
						this.reservationRepository.findAll().spliterator(), true)
						.collect(Collectors.toList())
		);
	}
	
	@Override
	@Transactional
	public List<ReservationPropositionDto> getPropositions(ReservationFilterDto reservationFilterDto) throws ReservationArgumentMethodNotValidException,
			ReservationTypeNotFoundException
	{
		if (!reservationFilterDto.fieldsAreNotNull())
			throw new ReservationArgumentMethodNotValidException("The filter fields must not be null");
		
		if (!ReservationType.isValidType(reservationFilterDto.getType().name()))
			throw new ReservationTypeNotFoundException("Invalid type");
		
		// we find first all the rooms that checks the type (and resources needed) and capacity
		JPAQuery<Room> roomJPAQuery = new JPAQuery<Room>(entityManager)
				.select(room)
				.from(room)
				.where(room.capacity.goe(reservationFilterDto.getPeopleNumber() / 0.7));
		
		// switch case for type/resource available
		switch (reservationFilterDto.getType())
		{
			case RC:
				roomJPAQuery.orderBy(room.hasTableau.desc(),
						room.hasEcran.desc(),
						room.hasPieuvre.desc(),
						room.hasWebcam.asc());
				break;
			case RS:
				roomJPAQuery.orderBy(room.hasTableau.asc(),
						room.hasEcran.asc(),
						room.hasPieuvre.asc(),
						room.hasWebcam.asc());
				break;
			case VC:
				roomJPAQuery.orderBy(room.hasEcran.desc(),
						room.hasPieuvre.desc(),
						room.hasWebcam.desc(),
						room.hasTableau.asc());
				break;
			case SPEC:
				roomJPAQuery.orderBy(room.hasTableau.desc(),
						room.hasEcran.asc(),
						room.hasPieuvre.asc(),
						room.hasWebcam.asc());
				break;
		}
		
		// we retrieve the first 3 better rooms for the reservation
		List<Room> roomList = roomJPAQuery.limit(3).fetch();
		Collections.reverse(roomList);
		
		// preparation for creating the list of propositions (up to 5 propositions)
		List<ReservationPropositionDto> reservationPropositionDtos = new ArrayList<>();
		
		// we check if the room is available in the desired hour, if so, we check if we need
		// to add extra equipments
		roomList.forEach(roomItem -> {
			JPAQuery<Reservation> reservationJPAQuery = new JPAQuery<Reservation>(entityManager)
					.select(reservation)
					.from(reservation)
					.where(reservation.startHour.between(reservationFilterDto.getStartHour().minusHours(1L), reservationFilterDto.getEndHour().plusHours(1L))
							.and(reservation.room.id.eq(roomItem.getId())));
			
			// if the hour is available
			if (reservationJPAQuery.fetchCount() == 0)
			{
				// list of available equipment at this hour
				List<Reservation> reservationsAtHour = this.listReservationsForAGivenHour(reservationFilterDto.getStartHour());
				Boolean extraPieuvreAvailable = 4 - reservationsAtHour.stream().filter(Reservation::getUseExtraPieuvre).count() > 0;
				Boolean extraScreenAvailable = 5 - reservationsAtHour.stream().filter(Reservation::getUseExtraEcran).count() > 0;
				Boolean extraBoardAvailable = 2 - reservationsAtHour.stream().filter(Reservation::getUseExtraTableau).count() > 0;
				Boolean extraWebcamAvailable = 4 - reservationsAtHour.stream().filter(Reservation::getUseExtraWebcam).count() > 0;
				
				ReservationPropositionDto reservationPropositionDto = ReservationPropositionDto.builder()
						.type(reservationFilterDto.getType())
						.startHour(reservationFilterDto.getStartHour())
						.endHour(reservationFilterDto.getEndHour())
						.peopleNumber(reservationFilterDto.getPeopleNumber())
						.roomId(roomItem.getId())
						.roomName(roomItem.getRoomName())
						.build();
				// we add extra equipment if we can/need else, there is no proposition
				switch (reservationFilterDto.getType())
				{
					case RC:
						Boolean invalidProposition = false;
						if (roomItem.getHasTableau())
						{
							reservationPropositionDto.setUseExtraTableau(false);
						}
						if (!roomItem.getHasTableau() && extraBoardAvailable)
						{
							reservationPropositionDto.setUseExtraTableau(true);
						}
						if (!roomItem.getHasTableau() && !extraBoardAvailable)
						{
							invalidProposition = true;
						}
						
						if (roomItem.getHasPieuvre())
						{
							reservationPropositionDto.setUseExtraPieuvre(false);
						}
						if (!roomItem.getHasPieuvre() && extraPieuvreAvailable)
						{
							reservationPropositionDto.setUseExtraPieuvre(true);
						}
						if (!roomItem.getHasPieuvre() && !extraPieuvreAvailable)
						{
							invalidProposition = true;
						}
						
						if (roomItem.getHasEcran())
						{
							reservationPropositionDto.setUseExtraEcran(false);
						}
						if (!roomItem.getHasEcran() && extraScreenAvailable)
						{
							reservationPropositionDto.setUseExtraEcran(true);
						}
						if (!roomItem.getHasEcran() && !extraScreenAvailable)
						{
							invalidProposition = true;
						}
						
						if (!invalidProposition)
						{
							reservationPropositionDto.setUseExtraWebcam(false);
							reservationPropositionDtos.add(reservationPropositionDto);
						}
						break;
					case VC:
						Boolean invalidProposition2 = false;
						if (roomItem.getHasWebcam())
						{
							reservationPropositionDto.setUseExtraWebcam(false);
						}
						if (!roomItem.getHasWebcam() && extraWebcamAvailable)
						{
							reservationPropositionDto.setUseExtraWebcam(true);
						}
						if (!roomItem.getHasWebcam() && !extraWebcamAvailable)
						{
							invalidProposition2 = true;
						}
						
						if (roomItem.getHasPieuvre())
						{
							reservationPropositionDto.setUseExtraPieuvre(false);
						}
						if (!roomItem.getHasPieuvre() && extraPieuvreAvailable)
						{
							reservationPropositionDto.setUseExtraPieuvre(true);
						}
						if (!roomItem.getHasPieuvre() && !extraPieuvreAvailable)
						{
							invalidProposition2 = true;
						}
						
						if (roomItem.getHasEcran())
						{
							reservationPropositionDto.setUseExtraEcran(false);
						}
						if (!roomItem.getHasEcran() && extraScreenAvailable)
						{
							reservationPropositionDto.setUseExtraEcran(true);
						}
						if (!roomItem.getHasEcran() && !extraScreenAvailable)
						{
							invalidProposition2 = true;
						}
						
						if (!invalidProposition2)
						{
							reservationPropositionDto.setUseExtraTableau(false);
							reservationPropositionDtos.add(reservationPropositionDto);
						}
						
						break;
					case SPEC:
						Boolean invalidProposition3 = false;
						if (roomItem.getHasTableau())
						{
							reservationPropositionDto.setUseExtraTableau(false);
						}
						if (!roomItem.getHasTableau() && extraBoardAvailable)
						{
							reservationPropositionDto.setUseExtraTableau(true);
						}
						if (!roomItem.getHasTableau() && !extraBoardAvailable)
						{
							invalidProposition3 = true;
						}
						
						if (!invalidProposition3)
						{
							reservationPropositionDto.setUseExtraWebcam(false);
							reservationPropositionDto.setUseExtraPieuvre(false);
							reservationPropositionDto.setUseExtraEcran(false);
							reservationPropositionDtos.add(reservationPropositionDto);
						}
						break;
				}
				
			}
		});
		
		// if the room isn't available in the desired hour, we check for an another hour
		// TODO: finish this method
		return reservationPropositionDtos;
	}
	
	/**
	 * Gives the list of the reservations for a given hour
	 *
	 * @param startHour the start hour of the reservations
	 *
	 * @return the list of reservations
	 */
	private List<Reservation> listReservationsForAGivenHour(LocalDateTime startHour)
	{
		JPAQuery<Reservation> reservationJPAQuery = new JPAQuery<Reservation>(entityManager)
				.select(reservation)
				.from(reservation)
				.where(reservation.startHour.eq(startHour));
		
		return reservationJPAQuery.fetch();
	}
	
	@Override
	@Transactional
	public Boolean saveReservation(ReservationPropositionDto reservationPropositionDto) throws ReservationAlreadyExistsException,
			ReservationArgumentMethodNotValidException, ReservationTypeNotFoundException
	{
		if (!reservationPropositionDto.fieldsAreNotNull())
			throw new ReservationArgumentMethodNotValidException("The proposition for saving a reservation has empty fields");
		
		if (!ReservationType.isValidType(reservationPropositionDto.getType().name()))
			throw new ReservationTypeNotFoundException("Invalid type");
		
		try
		{
			this.reservationRepository.save(this.reservationMapper.reservationPropositionDtoToReservation(reservationPropositionDto));
		}
		catch (Exception exception)
		{
			throw new ReservationAlreadyExistsException(exception.getMessage());
		}
		
		return true;
	}
}
