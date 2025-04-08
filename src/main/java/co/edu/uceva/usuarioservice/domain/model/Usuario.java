package co.edu.uceva.usuarioservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    private String nombreCompleto;
    @NotEmpty(message = "No puede estar vacio")
    @Size(max = 255)
    @Column(nullable = false)
    @Email(message = "Debe ser un correo valido example@some.com")
    private String correo;
    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 8, max = 255)
    @Column(nullable = false)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial (#$@!%&*?)"
    )
    private String contrasena;
    @Min(value = 0, message = "No puede ser negativo")
    @NotNull(message = "Su cedula no puede quedar sin un valor.")
    @Column(nullable = false)
    private long cedula;
    @NotNull(message = "Su numero de telefono no puede quedar sin un valor.")
    @Column(nullable = false)
    private long telefono;
    @NotEmpty(message = "No puede estar vacio")
    @Pattern(
            regexp = "^(Estudiante|Docente|Administrativo|Decano|Rector|Administrador)$",
            message = "El rol debe ser uno de los siguientes: Estudiante, Docente, Administrativo, Decano, Rector o Administrador"
    )
    private String rol;
}
