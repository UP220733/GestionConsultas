package com.consulta.controller;

import com.consulta.model.Paciente;
import com.consulta.repository.PacienteRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class PacienteController {

    private final PacienteRepository repo;

    public PacienteController(PacienteRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public Paciente registrar(@RequestBody Paciente p) {
        return repo.save(p);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> cred) {
        Optional<Paciente> p = repo.findByCorreo(cred.get("correo"));
        if (p.isPresent() && p.get().getPassword().equals(cred.get("password"))) {
            return ResponseEntity.ok(p.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}
