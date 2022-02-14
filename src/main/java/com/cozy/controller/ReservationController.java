package com.cozy.controller;

import com.cozy.model.Reservation;
import com.cozy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * addReservation: Resident creates a new reservation
     * Method: POST
     * Endpoint: /{userId}/reservations
     */
    @PostMapping("/{userId}/reservations")
    public Reservation addReservation(@PathVariable (value = "userId") int userId,
                               @RequestBody Reservation reservation) {
        return reservationService.add(userId, reservation);
    }

    /**
     * listReservationsByUser: Resident lists all of his or her past reservations
     * Method: GET
     * Endpoint: /{userId}/reservations
     */
    @GetMapping("/{userId}/reservations")
    public List<Reservation> listReservationsByUser(
            @PathVariable (value = "userId") int userId) {
        return reservationService.getAllByUser(userId);
    }

    /**
     * listReservations: Admin lists all reservations that haven't been processed
     * Method: GET
     * Endpoint: /reservations
     */
    @GetMapping("/reservations")
    public List<Reservation> listReservations() {
        return reservationService.getAll();
    }

    /**
     * editReservation: Admin changes the state of a reservation
     * Method: PUT
     * Endpoint: /reservations/{reservationId}
     */
    @PutMapping("/reservations/{reservationId}")
    public void editReservation(@PathVariable int reservationId,
                                @RequestBody Reservation reservationRequest) {
        reservationService.put(reservationId, reservationRequest);
    }

    /**
     * deleteReservation: Admin deletes a reservation after changing its state
     * Method: DELETE
     * Endpoint: /reservations/{reservationId}
     */
    @DeleteMapping("/reservations/{reservationId}")
    public void deleteReservation(@PathVariable int reservationId) {
        reservationService.delete(reservationId);
    }

}
