package co.edu.uceva.usuarioservice.domain.service;

import co.edu.uceva.usuarioservice.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(long id);
    Usuario update(Usuario usuario);
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    Page<Usuario> findAll(Pageable pageable);
}
