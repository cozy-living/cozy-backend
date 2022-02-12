package com.cozy.repository;

import com.cozy.model.Reservation;
import com.cozy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
//    List<Reservation> findByResidentId(User residentId);
}
