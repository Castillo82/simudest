package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Eleccion;
import com.simudest.simudest.entity.EleccionId;
import com.simudest.simudest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, EleccionId> {


    public Optional<Eleccion> findByUsuarioAndOrden(Usuario usuario, Integer orden);

}
