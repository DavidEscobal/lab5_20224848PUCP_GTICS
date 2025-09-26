package org.example.laboratorio3.repository;

import org.example.laboratorio3.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// este repositorio es muy imporante pues maneja los usuarios

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByCorreo(String correo);

    // Ranking por mensajes RECIBIDOS (LEFT JOIN para incluir a los de 0)
    @Query("""
           SELECT u, COUNT(m.id)
           FROM Usuarios u
           LEFT JOIN Mensajes m ON m.destinatario = u
           GROUP BY u
           ORDER BY COUNT(m.id) DESC, u.apellido ASC, u.nombre ASC
           """)
    List<Object[]> rankingEnVivo();
}
