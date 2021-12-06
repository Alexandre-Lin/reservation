package com.project.reservation.repository;

import com.project.reservation.entity.Reservation;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>, QuerydslPredicateExecutor<Reservation>
{
}
