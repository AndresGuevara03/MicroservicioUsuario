package co.edu.uceva.usuarioservice.delibery.rest;

import co.edu.uceva.usuarioservice.domain.model.Usuario;
import co.edu.uceva.usuarioservice.domain.service.IUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {
    private final IUsuarioService usuarioService;

    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/page/{page}")
    public Page<Usuario> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return usuarioService.findAll(pageable);
    }

    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/usuarios")
    public void delete(@RequestBody Usuario usuario) {
        usuarioService.delete(usuario);
    }

    @PutMapping("/usuarios")
    public Usuario update(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }
}
