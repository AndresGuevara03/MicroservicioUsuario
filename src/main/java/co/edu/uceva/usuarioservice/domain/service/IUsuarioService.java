package co.edu.uceva.usuarioservice.domain.service;

import co.edu.uceva.usuarioservice.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> findAll();
    public Optional<Usuario> findById(long id);
    public Usuario update(Usuario usuario);
    public Usuario save(Usuario usuario);
    public void delete(Usuario usuario);
    public Page<Usuario> findAll(Pageable pageable);
}
