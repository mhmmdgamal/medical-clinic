package com.stc.clinic.service;

import com.stc.clinic.entitiy.Appointment;
import com.stc.clinic.entitiy.Patient;
import com.stc.clinic.dto.AppointmentDto;
import com.stc.clinic.dto.AppointmentFilter;
import com.stc.clinic.repository.AppointmentRepository;
import com.stc.clinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public List<Appointment> findTodayAppointments() {
        return appointmentRepository.findAllByStartDateBetween(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0),
                LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0));
    }

    @Transactional
    public Appointment addNewAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient with id: " + appointmentDto.getPatientId() + " is not found"));

        Appointment appointment = new Appointment();
        appointment.setStartDate(appointmentDto.getStart());
        appointment.setEndDate(appointmentDto.getEnd());
        appointment.setPatient(patient);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void cancelAppointment(Long id, String reason) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no appointment with id: " + id));

        appointment.setCancelReason(reason);
        appointmentRepository.saveAndFlush(appointment);
        appointmentRepository.delete(appointment);
    }

    public List<Appointment> findPatientAppointmentsHistory(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("There is no patient with id: " + patientId));

        return patient.getAppointments();
    }

    public List<Appointment> filterAppointments(AppointmentFilter filter) {
        return null;
    }
}
