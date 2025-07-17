package com.consulta.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Cita {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    // Getters y Setters
}
