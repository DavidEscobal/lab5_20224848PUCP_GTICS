package org.example.laboratorio3.service;

import org.example.laboratorio3.entity.Ranking;
import org.example.laboratorio3.entity.Usuarios;
import org.example.laboratorio3.repository.RankingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//esrta seccion es para el ranking de usuarios que más regalos han recibido

@Service
public class RankingService {
    private final RankingRepository repo;
    public RankingService(RankingRepository repo) { this.repo = repo; }

    public List<Ranking> listarOrdenado() { return repo.listarOrdenado(); }

    @Transactional
    public void ensureRow(Usuarios u) {
        repo.findById(u.getId()).orElseGet(() -> repo.save(new Ranking(u)));
    }

    @Transactional
    public void incrementar(Usuarios usuario) {
        Ranking r = repo.findById(usuario.getId()).orElse(new Ranking(usuario));
        r.setTotalRegalos((r.getTotalRegalos()==null?0:r.getTotalRegalos()) + 1);
        repo.save(r);
    }
}
