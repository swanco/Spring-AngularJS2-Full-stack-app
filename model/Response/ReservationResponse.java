package com.swanco.fullstackappboot.model.Response;

import java.time.LocalDate;

import com.swanco.fullstackappboot.model.Links;

public class ReservationResponse {
	private long id;

    private LocalDate checkin;
    private LocalDate checkout;
	
	public ReservationResponse() {
		super();
	}

	public ReservationResponse(long id, LocalDate checkin, LocalDate checkout) {
		super();
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	
}
