package com.consulta.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulta.model.Paciente;
import com.consulta.repository.PacienteRepository;

@CrossOrigin(origins = "*")
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
