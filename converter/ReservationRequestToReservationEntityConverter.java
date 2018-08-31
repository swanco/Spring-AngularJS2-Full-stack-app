package com.swanco.fullstackappboot.converter;

import org.springframework.core.convert.converter.Converter;

import com.swanco.fullstackappboot.entity.ReservationEntity;
import com.swanco.fullstackappboot.model.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {

    @Override
    public ReservationEntity convert(ReservationRequest source) {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());
        if (source.getId()!=0)
            reservationEntity.setId(source.getId());

        return reservationEntity;
    }
}