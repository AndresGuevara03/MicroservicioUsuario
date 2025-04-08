package co.edu.uceva.usuarioservice.domain.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(long id) {
        super("El usuario con id '" + id + "' ya existe.");
    }
}
