package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT us FROM Usuario us  WHERE us.email=(:email)")
    public Optional<Usuario> findByEmail(@Param("email") String email);

}
