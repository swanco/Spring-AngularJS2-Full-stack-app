package com.swanco.fullstackappboot.converter;

import org.springframework.core.convert.converter.Converter;

import com.swanco.fullstackappboot.entity.RoomEntity;
import com.swanco.fullstackappboot.model.Links;
import com.swanco.fullstackappboot.model.Self;
import com.swanco.fullstackappboot.model.Response.ReservableRoomResponse;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {
		// TODO Auto-generated method stub
		
		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice( Integer.valueOf(source.getPrice()) );
		
		Links links = new Links();
		Self self = new Self();
		self.setRef("/rooms/reservation/v1/"+ source.getId());
		links.setSelf(self);
		
		reservationResponse.setLinks(links);
		
		return reservationResponse;
	}

	
	
}