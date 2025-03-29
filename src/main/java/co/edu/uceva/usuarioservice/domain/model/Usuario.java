package co.edu.uceva.usuarioservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private long cedula;
    private long telefono;
    private String rol;
}
