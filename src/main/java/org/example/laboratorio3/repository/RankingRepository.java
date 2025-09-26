package org.example.laboratorio3.repository;

import org.example.laboratorio3.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// este repositorio es muy imporante pues maneja el ranking de usuarios por regalos enviados
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

    @Query("""
           SELECT r
           FROM Ranking r
           JOIN FETCH r.usuario u
           ORDER BY r.totalRegalos DESC, u.apellido ASC, u.nombre ASC
           """)
    List<Ranking> listarOrdenado();
}
