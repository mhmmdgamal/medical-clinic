package com.stc.clinic.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table
@Getter
@Setter
@SQLDelete(sql = "UPDATE appointment SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Appointment {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_seq")
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonIgnore
    private String cancelReason;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    private Patient patient;

    @JsonIgnore
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment )) return false;
        return id != null && id.equals(((Appointment) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}