package org.example.laboratorio3.service;

import org.example.laboratorio3.entity.Usuarios;
import org.example.laboratorio3.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// esta seccion es para la gestion de usuarios

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuarios> listar() { return repo.findAll(); }
    public Optional<Usuarios> porId(Integer id) { return repo.findById(id); }
    public Usuarios guardar(Usuarios u) { return repo.save(u); }
    public void eliminar(Integer id) { repo.deleteById(id); }

    public List<Object[]> rankingEnVivo() { return repo.rankingEnVivo(); }
}
