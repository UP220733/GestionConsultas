package com.consulta.controller;

import com.consulta.model.Medico;
import com.consulta.repository.MedicoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repo;

    public MedicoController(MedicoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Medico> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Medico create(@RequestBody Medico m) {
        return repo.save(m);
    }
}
