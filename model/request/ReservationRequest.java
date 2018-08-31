package com.swanco.fullstackappboot.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class ReservationRequest {
private long id;
@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
LocalDate checkin;
@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
LocalDate checkout;




public ReservationRequest() {
	super();
}
public ReservationRequest(long id, LocalDate checkin, LocalDate checkout) {
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
