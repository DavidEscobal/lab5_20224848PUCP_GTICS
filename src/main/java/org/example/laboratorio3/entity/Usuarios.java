package org.example.laboratorio3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// este entity representa a los usuarios del sistema

@Entity
@Table(name = "usuarios")
public class Usuarios{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "Solo se permiten letras y espacios"
    )
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "Solo se permiten letras y espacios"
    )
    private String apellido;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Formato de correo inválido"
    )
    @Column(unique = true)
    private String correo;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "Edad mínima 18 años")
    private Integer edad;

    @Size(max = 255, message = "Máximo 255 caracteres")
    @Pattern(
            regexp = "^.{0}|.{10,}$",
            message = "Si ingresas descripción, debe tener al menos 10 caracteres"
    )
    private String descripcion;


    @Size(max = 255, message = "Máximo 255 caracteres")
    @Pattern(
            regexp = "^$|^(?=.*\\d).{6,}$",
            message = "Debe tener al menos 6 caracteres y un número"
    )
    @Column(name = "contrasena")
    private String contrasena;

    @PrePersist @PreUpdate
    private void trimStrings() {
        if (nombre != null) nombre = nombre.trim();
        if (apellido != null) apellido = apellido.trim();
        if (correo != null) correo = correo.trim();
        if (descripcion != null) descripcion = descripcion.trim();
        if (contrasena != null) contrasena = contrasena.trim();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
