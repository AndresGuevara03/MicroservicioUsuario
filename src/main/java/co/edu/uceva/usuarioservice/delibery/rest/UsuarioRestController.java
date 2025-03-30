package co.edu.uceva.usuarioservice.delibery.rest;

import co.edu.uceva.usuarioservice.domain.exception.NoHayUsuariosException;
import co.edu.uceva.usuarioservice.domain.exception.PaginaSinUsuariosException;
import co.edu.uceva.usuarioservice.domain.exception.UsuarioNoEncontradoException;
import co.edu.uceva.usuarioservice.domain.exception.ValidationException;
import co.edu.uceva.usuarioservice.domain.model.Usuario;
import co.edu.uceva.usuarioservice.domain.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {
    private final IUsuarioService usuarioService;

    private static final String MENSAJE = "mensaje";
    private static final String USUARIO = "usuario";
    private static final String USUARIOS = "usuarios";

    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            throw new NoHayUsuariosException();
        }
        response.put(USUARIOS, usuarios);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.findAll(pageable);
        if (usuarios.isEmpty()) {
            throw new PaginaSinUsuariosException(page);
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Usuario nuevoUsuario = usuarioService.save(usuario);
        response.put(MENSAJE, "El usuario ha sido creado con éxito!");
        response.put(USUARIO, nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        usuarioService.findById(usuario.getId()).orElseThrow(
                () -> new UsuarioNoEncontradoException(usuario.getId())
        );
        usuarioService.delete(usuario);
        response.put(MENSAJE, "El usuario ha sido eliminado con éxito!");
        response.put(USUARIO, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        usuarioService.findById(usuario.getId()).orElseThrow(
                () -> new UsuarioNoEncontradoException(usuario.getId())
        );
        Usuario usuarioActualizado = usuarioService.update(usuario);
        response.put(MENSAJE, "El usuario ha sido actualizado con éxito!");
        response.put(USUARIO, usuarioActualizado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = usuarioService.findById(id).orElseThrow(
                () -> new UsuarioNoEncontradoException(id)
        );
        response.put(MENSAJE, "El usuario ha sido encontrado!");
        response.put(USUARIO, usuario);
        return ResponseEntity.ok(response);
    }
}
