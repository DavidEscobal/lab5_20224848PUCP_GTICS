package org.example.laboratorio3.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

// Este entity representa los mensajes enviados entre usuarios, incluyendo detalles sobre el regalo asociado.

@Entity
@Table(name = "mensajes")
public class Mensajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuarios remitente;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuarios destinatario;


    @Enumerated(EnumType.STRING)
    @Column(name = "regalo_tipo", nullable = false, length = 10)
    private RegaloTipo regaloTipo;

    @Size(max = 30)
    @Column(name = "regalo_color", length = 30)
    private String regaloColor;

    @NotBlank
    @Size(max = 255)
    private String contenido;

    @Column(name = "fecha_envio", insertable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuarios getRemitente() { return remitente; }
    public void setRemitente(Usuarios remitente) { this.remitente = remitente; }

    public Usuarios getDestinatario() { return destinatario; }
    public void setDestinatario(Usuarios destinatario) { this.destinatario = destinatario; }

    public RegaloTipo getRegaloTipo() { return regaloTipo; }
    public void setRegaloTipo(RegaloTipo regaloTipo) { this.regaloTipo = regaloTipo; }

    public String getRegaloColor() { return regaloColor; }
    public void setRegaloColor(String regaloColor) { this.regaloColor = regaloColor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
