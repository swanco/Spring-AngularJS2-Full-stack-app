package com.swanco.fullstackappboot.rest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swanco.fullstackappboot.converter.RoomEntityToReservableRoomResponseConverter;
import com.swanco.fullstackappboot.entity.ReservationEntity;
import com.swanco.fullstackappboot.entity.RoomEntity;
import com.swanco.fullstackappboot.model.Response.ReservableRoomResponse;
import com.swanco.fullstackappboot.model.Response.ReservationResponse;
import com.swanco.fullstackappboot.model.request.ReservationRequest;
import com.swanco.fullstackappboot.repository.PageableRoomRepository;
import com.swanco.fullstackappboot.repository.ReservationRepository;
import com.swanco.fullstackappboot.repository.RoomRepository;

@RestController
@RequestMapping("/rooms/reservation/v1")
public class ReservationResource {
	  @Autowired
      PageableRoomRepository pageableRoomRepository;

      @Autowired
      RoomRepository roomRepository;

      @Autowired
      ReservationRepository reservationRepository;

      @Autowired
      ConversionService conversionService;

  @RequestMapping(path ="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public <U> Page<? extends U> getAvailableRooms (
          @RequestParam(value = "checkin")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                  LocalDate checkin,
          @RequestParam(value = "checkout")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                  LocalDate checkout, Pageable pageable) {

      Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

      return roomEntityList.map((Function<? super RoomEntity, ? extends U>) new RoomEntityToReservableRoomResponseConverter());
  }

  @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RoomEntity> getRoomById(
          @PathVariable
                  Long roomId) {

      RoomEntity roomEntity = roomRepository.findByIdnew(roomId);
      
      return new ResponseEntity<>(roomEntity, HttpStatus.OK);
  }

  @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<ReservationResponse> createReservation(
          @RequestBody
          ReservationRequest reservationRequest) {

          ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
          reservationRepository.save(reservationEntity);

          RoomEntity roomEntity = roomRepository.findByIdnew(reservationRequest.getId());
          roomEntity.addReservationEntity(reservationEntity);
          roomRepository.save(roomEntity);
          reservationEntity.setRoomEntity(roomEntity);

          ReservationResponse reservationResponse =
                  conversionService.convert(reservationEntity, ReservationResponse.class);


          return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
  }

  @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<ReservableRoomResponse> updateReservation(
          @RequestBody
          ReservationRequest reservationRequest) {

      return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
  }

  @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteReservation(
          @PathVariable
          long reservationId) {

      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
