package com.swanco.fullstackappboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.swanco.fullstackappboot.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
	
	

	RoomEntity findByIdnew(Long roomId);
}
