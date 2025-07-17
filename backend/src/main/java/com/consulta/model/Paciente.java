package com.consulta.model;

import jakarta.persistence.*;

@Entity
public class Paciente {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String correo;
    private String password;

    // Getters y Setters
}
