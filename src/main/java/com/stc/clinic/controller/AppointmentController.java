package com.stc.clinic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stc.clinic.entitiy.Appointment;
import com.stc.clinic.dto.AppointmentDto;
import com.stc.clinic.dto.AppointmentFilter;
import com.stc.clinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("")
    public List<Appointment> findTodayAppointments() {
        return appointmentService.findTodayAppointments();
    }

    @PostMapping("")
    public Appointment addAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.addNewAppointment(appointmentDto);
    }

    @DeleteMapping("/{id}")
    public void cancelAppointment(@PathVariable Long id, @RequestParam String reason) {
        appointmentService.cancelAppointment(id, reason);
    }

    @GetMapping("/{patientId}")
    public List<Appointment> findPatientAppointmentsHistory(@PathVariable Long patientId) {
        return appointmentService.findPatientAppointmentsHistory(patientId);
    }

    @GetMapping("/filter")
    public List<Appointment> findPatientAppointmentsHistory(@RequestParam String filter) throws JsonProcessingException {
        AppointmentFilter appointmentFilter = new ObjectMapper().readValue(filter, AppointmentFilter.class);
        return appointmentService.filterAppointments(appointmentFilter);
    }
}
