package com.cozy.controller;

import com.cozy.common.ServiceType;
import com.cozy.model.Reservation;
import com.cozy.model.User;
import com.cozy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservationController {
    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.add(reservation);
    }

//    @PostMapping("/reservation")
//    public void addReservation(
//            @RequestParam("type") ServiceType type,
//            @RequestParam("datetime") LocalDate datetime,
//            @RequestParam("state") String state,
//            @RequestParam("residentId") User residentId) {
//
//        Reservation reservation= new Reservation.Builder()
//                .setResident(residentId)
//                .setType(type)
//                .setDatetime(datetime)
//                .setState(state)
//                .build();
//        reservationService.add(reservation);
//    }

    //admin get list of reservations
    @GetMapping("/reservation/all")
    public List<Reservation> listReservations(Reservation reservation) {
        return reservationService.reservations(reservation);
    }

    //admin reject the reservation
    @DeleteMapping("/reservation/{reservationId}")
    public void deleteReservation(@PathVariable int reservationId) {
        reservationService.delete(reservationId);
    }


}
