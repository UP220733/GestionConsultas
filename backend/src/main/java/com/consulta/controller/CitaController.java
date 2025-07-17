package com.consulta.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulta.model.Cita;
import com.consulta.model.Medico;
import com.consulta.model.Paciente;
import com.consulta.repository.CitaRepository;
import com.consulta.repository.MedicoRepository;
import com.consulta.repository.PacienteRepository;

@RestController
@CrossOrigin(origins = "*")
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
