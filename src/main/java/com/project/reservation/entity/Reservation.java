package com.project.reservation.entity;

import com.project.reservation.enums.ReservationType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


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
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ReservationType type;
	
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
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reservation that = (Reservation) o;
		return Objects.equals(id, that.id) && type == that.type && Objects.equals(startHour, that.startHour) && Objects.equals(endHour, that.endHour) && Objects
				.equals(peopleNumber, that.peopleNumber) && Objects.equals(room, that.room);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(id, type, startHour, endHour, peopleNumber, room);
	}
}
