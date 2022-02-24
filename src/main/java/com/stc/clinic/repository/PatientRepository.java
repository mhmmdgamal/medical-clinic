package com.stc.clinic.repository;

import com.stc.clinic.entitiy.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}
