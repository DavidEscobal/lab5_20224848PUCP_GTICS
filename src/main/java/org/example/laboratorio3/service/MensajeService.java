package org.example.laboratorio3.service;

import org.example.laboratorio3.entity.Mensajes;
import org.example.laboratorio3.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// esta seccion es para el envío y recepción de mensajes

@Service
public class MensajeService {
    private final MensajeRepository repo;

    public MensajeService(MensajeRepository repo) {
        this.repo = repo;
    }

    public Mensajes enviar(Mensajes m) { return repo.save(m); }
    public List<Mensajes> recibidos(Integer usuarioId) { return repo.recibidos(usuarioId); }
    public long totalRecibidos(Integer usuarioId) { return repo.totalRecibidos(usuarioId); }
}
