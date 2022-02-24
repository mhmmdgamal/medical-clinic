package com.stc.clinic.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq")
    private Long id;

    @Basic(optional = false)
    private String name;

    @OneToMany(mappedBy = "patient", cascade = ALL, orphanRemoval = true)
    List<Appointment> appointments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient )) return false;
        return id != null && id.equals(((Patient) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}