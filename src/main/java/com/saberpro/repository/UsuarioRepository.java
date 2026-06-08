package com.saberpro.repository;

import com.saberpro.model.Usuario;
import com.saberpro.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByRol(Rol rol);
    boolean existsByUsername(String username);
}
