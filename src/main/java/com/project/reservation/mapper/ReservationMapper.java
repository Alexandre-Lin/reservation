package com.project.reservation.mapper;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.entity.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReservationMapper
{
	@Mapping(source = "room.roomName", target = "roomName")
	ReservationConsulterDto reservationToReservationConsulterDto(Reservation reservation);
	
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<ReservationConsulterDto> reservationsToReservationConsulterDtos(List<Reservation> reservations);
	
	@Mapping(source = "room.id", target = "roomId")
	ReservationPropositionDto reservationToReservationPropositionDto(Reservation reservation);
	
	@IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
	List<ReservationPropositionDto> reservationsToReservationPropositionDtos(List<Reservation> reservations);
	
	@Mapping(source = "roomId", target = "room.id")
	Reservation eservationPropositionDtoToReservation(ReservationPropositionDto reservation);
}
