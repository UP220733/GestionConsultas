package com.consulta.controller;

import com.consulta.model.*;
import com.consulta.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaRepository citaRepo;
    private final MedicoRepository medicoRepo;
    private final PacienteRepository pacienteRepo;

    public CitaController(CitaRepository citaRepo,
                          MedicoRepository medicoRepo,
                          PacienteRepository pacienteRepo) {
        this.citaRepo = citaRepo;
        this.medicoRepo = medicoRepo;
        this.pacienteRepo = pacienteRepo;
    }

    @PostMapping
    public Cita agendar(@RequestBody Map<String, String> datos) {
        Paciente paciente = pacienteRepo.findById(Long.parseLong(datos.get("pacienteId")))
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        Medico medico = medicoRepo.findById(Long.parseLong(datos.get("medicoId")))
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));

        Cita c = new Cita();
        c.setFecha(LocalDate.parse(datos.get("fecha")));
        c.setPaciente(paciente);
        c.setMedico(medico);

        return citaRepo.save(c);
    }

    @GetMapping("/paciente/{id}")
    public List<Cita> porPaciente(@PathVariable Long id) {
        return citaRepo.findByPacienteId(id);
    }

    @GetMapping
    public List<Cita> todas() {
        return citaRepo.findAll();
    }
}
