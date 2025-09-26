package org.example.laboratorio3.repository;

import org.example.laboratorio3.entity.Mensajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// este repositorio es muy imporante pues maneja los mensajes entre usuarios

public interface MensajeRepository extends JpaRepository<Mensajes, Integer> {

    @Query("""
           SELECT m FROM Mensajes m
           WHERE m.destinatario.id = :usuarioId
           ORDER BY m.fechaEnvio DESC
           """)
    List<Mensajes> recibidos(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(m) FROM Mensajes m WHERE m.destinatario.id = :usuarioId")
    long totalRecibidos(@Param("usuarioId") Integer usuarioId);
}
