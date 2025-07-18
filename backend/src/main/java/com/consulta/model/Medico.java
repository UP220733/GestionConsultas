package com.consulta.model;

import jakarta.persistence.*;

@Entity
public class Medico {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String especialidad;
    private String horario;

    // Getters y Setters
}
