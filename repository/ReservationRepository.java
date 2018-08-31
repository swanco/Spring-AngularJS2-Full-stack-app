package com.swanco.fullstackappboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.swanco.fullstackappboot.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
	
}
