package com.stc.clinic.repository;

import com.stc.clinic.entitiy.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end);
}
