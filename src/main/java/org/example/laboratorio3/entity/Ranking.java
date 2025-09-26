package org.example.laboratorio3.entity;

import jakarta.persistence.*;

//este entity representa el ranking de usuarios basado en la cantidad de regalos recibidos

@Entity
@Table(name="ranking")
public class Ranking {

    @Id
    @Column(name="usuario_id")
    private Integer usuarioId;

    @OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="usuario_id")
    private Usuarios usuario;

    @Column(name="total_regalos")
    private Integer totalRegalos = 0;

    public Ranking() {}
    public Ranking(Usuarios usuario) {
        this.usuario = usuario;
        this.usuarioId = usuario.getId();
        this.totalRegalos = 0;
    }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }
    public Integer getTotalRegalos() { return totalRegalos; }
    public void setTotalRegalos(Integer totalRegalos) { this.totalRegalos = totalRegalos; }
}
