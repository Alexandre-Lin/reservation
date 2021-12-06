package com.project.reservation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


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
}
