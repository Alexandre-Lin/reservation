package com.project.reservation.repository;

import com.project.reservation.entity.Room;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>, QuerydslPredicateExecutor<Room>
{
}
