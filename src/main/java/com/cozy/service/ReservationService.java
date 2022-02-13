package com.cozy.service;

import com.cozy.exception.ResourceNotFoundException;
import com.cozy.model.Reservation;
import com.cozy.model.User;
import com.cozy.repository.ReservationRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public Reservation add(int userId, Reservation reservation) {
        User user = userRepository.findById(userId);
//        List<Reservation> reservations = user.getReservations();
//        reservations.add(reservation);
//        user.setReservations(reservations);
        reservation.setUser(user);
        reservationRepository.save(reservation);
        return reservation;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllByUser(int userId) {
        return reservationRepository.findAllByUserId(userId);
    }

    public void put(int reservationId, Reservation reservationRequest) {
        reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setState(reservationRequest.getState());
            reservationRepository.save(reservation);
            return reservation;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("reservation id " + reservationId + " not found"));
    }

    public ResponseEntity<?> delete(int reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("reservation id " + reservationId + " not found"));
    }
}
