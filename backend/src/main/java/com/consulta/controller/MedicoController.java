package com.consulta.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulta.model.Medico;
import com.consulta.repository.MedicoRepository;

@CrossOrigin(origins = "*")
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
