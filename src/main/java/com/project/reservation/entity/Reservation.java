package com.project.reservation.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
public class Reservation
{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Integer id;
	
	@Column(name = "type", nullable = false)
	@Getter
	@Setter
	private String type;
	
	@Column(name = "hour_reservation_start", nullable = false)
	@Getter
	@Setter
	private LocalDateTime startHour;
	
	@Column(name = "hour_reservation_end", nullable = false)
	@Getter
	@Setter
	private LocalDateTime endHour;
	
	@Column(name = "people_number", nullable = false)
	@Getter
	@Setter
	private Integer peopleNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_room_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private Room room;
}
