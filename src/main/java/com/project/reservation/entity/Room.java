package com.project.reservation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
public class Room
{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Integer id;
	
	@Column(name = "room_number", nullable = false)
	@Getter
	@Setter
	private String roomName;
	
	@Column(name = "capacity", nullable = false)
	@Getter
	@Setter
	private Integer capacity;
	
	@Column(name = "specification_ecran")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter
	@Setter
	private Boolean hasEcran;
	
	@Column(name = "specification_pieuvre")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter
	@Setter
	private Boolean hasPieuvre;
	
	@Column(name = "specification_tableau")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter
	@Setter
	private Boolean hasTableau;
	
	@Column(name = "specification_webcam")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter
	@Setter
	private Boolean hasWebcam;
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return Objects.equals(id, room.id) && Objects.equals(roomName, room.roomName) && Objects.equals(capacity, room.capacity) && Objects
				.equals(hasEcran, room.hasEcran) && Objects.equals(hasPieuvre, room.hasPieuvre) && Objects.equals(hasTableau, room.hasTableau) && Objects
				.equals(hasWebcam, room.hasWebcam);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(id, roomName, capacity, hasEcran, hasPieuvre, hasTableau, hasWebcam);
	}
}
