package com.cozy.service;

import com.cozy.model.Reservation;
import com.cozy.model.User;
import com.cozy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    List<Reservation> reservationList = new ArrayList<>();

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //add reservation
    public void add(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    //list of resident's reservation
//    public List<Reservation> listByResidentId(User residentId) {
//        return reservationRepository.findByResidentId(residentId);
//    }

    //list of reservations
    public List<Reservation> reservations(Reservation reservation) {
        return reservationRepository.findAll();
    }

//    Decline the reservation
    public void delete(int reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
