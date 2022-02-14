package com.cozy.service;

import com.cozy.commons.UserRole;
import com.cozy.exception.ResourceNotFoundException;
import com.cozy.exception.UserNotAuthorizedException;
import com.cozy.model.Reservation;
import com.cozy.model.User;
import com.cozy.repository.ReservationRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
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

    public void put(int userId, int reservationId, Reservation reservationRequest)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        User user = userRepository.getById(userId);
        // Only tha admin can edit a reservation made by a resident
        if (!user.getRole().equals(UserRole.ADMIN.name())) {
            log.error("The user is not authorized to edit this reservation!");
            throw new UserNotAuthorizedException("The user is not authorized to edit this reservation!");
        }
        reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setState(reservationRequest.getState());
            reservationRepository.save(reservation);
            return reservation;
        }).orElseThrow(() -> new ResourceNotFoundException
                ("reservation id " + reservationId + " not found"));
    }

    public ResponseEntity<?> delete(int userId, int reservationId)
            throws UserNotAuthorizedException, ResourceNotFoundException {
        User user = userRepository.getById(userId);
        // Only tha admin can delete a reservation made by a resident
        if (!user.getRole().equals(UserRole.ADMIN.name())) {
            log.error("The user is not authorized to delete this reservation!");
            throw new UserNotAuthorizedException("The user is not authorized to delete this reservation!");
        }
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException
                ("reservation id " + reservationId + " not found"));
    }
}
