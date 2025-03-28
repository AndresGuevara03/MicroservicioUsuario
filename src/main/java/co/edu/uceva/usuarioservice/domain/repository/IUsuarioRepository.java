package co.edu.uceva.usuarioservice.domain.repository;

import co.edu.uceva.usuarioservice.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
