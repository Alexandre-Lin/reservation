package com.project.reservation.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.project.reservation.controller.impl.ReservationControllerImpl;
import com.project.reservation.dto.ReservationConsulterDto;
import com.project.reservation.enums.ReservationType;
import com.project.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ReservationControllerImpl.class)
class ReservationControllerTest
{
	@MockBean
	private ReservationService reservationService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getReservations() throws Exception
	{
		LocalDateTime date = LocalDateTime.now();
		ReservationConsulterDto reservationConsulterDto =
				ReservationConsulterDto.builder().endHour(date.plusHours(1L)).startHour(date).peopleNumber(7).type(ReservationType.RC).roomName("E1002").build();
		Mockito.when(reservationService.getAllReservations()).thenReturn(Collections.singletonList(reservationConsulterDto));
		
		// to convert object to JSON (with LocalDateTime converted as a string and not object)
		JavaTimeModule module = new JavaTimeModule();
		LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.modules(module)
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.build();
		
		// test
		mockMvc.perform(get("/api/reservation/all"))
				// check status
				.andExpect(status().isOk())
				// check content
				.andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(reservationConsulterDto))));
	}
	
	// TODO: tests
	@Test
	void getPropositions()
	{
	}
	
	@Test
	void getDesiredProposition()
	{
	}
}
