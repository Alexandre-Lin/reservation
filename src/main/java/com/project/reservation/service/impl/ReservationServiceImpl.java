package com.project.reservation.service.impl;

import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.dto.ReservationFilterDto;
import com.project.reservation.dto.ReservationPropositionDto;
import com.project.reservation.mapper.ReservationMapper;
import com.project.reservation.repository.ReservationRepository;
import com.project.reservation.repository.RoomRepository;
import com.project.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	//TODO: implements all methods
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
	public List<ReservationPropositionDto> getPropositions(ReservationFilterDto reservationFilterDto)
	{
		return null;
	}
	
	@Override
	@Transactional
	public Boolean saveReservation(ReservationPropositionDto reservationPropositionDto)
	{
		return null;
	}
}
