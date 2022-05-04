package com.simudest.simudest.repository;

import com.simudest.simudest.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, EleccionId> {

    /* esto no puede funcionar, pq un usuario puede elegir en varias convocatorias....*/
    //public Optional<Eleccion> findByUsuarioAndOrden(Usuario usuario, Integer orden);

    @Query("SELECT el FROM Eleccion el, Plaza pl WHERE pl.convocatoria=(:convocatoria) and el.usuario=(:opositor) and el.plaza=pl.id and el.orden=(:orden)")
    public Optional<Eleccion> findByUsuarioAndOrdenAndConvocatoria(@Param("opositor") Usuario usuario, @Param("orden") Integer orden, @Param("convocatoria") Convocatoria convocatoria);


    @Query("SELECT el FROM Eleccion el, Plaza pl WHERE pl.convocatoria=(:convocatoria) and el.usuario=(:opositor) and el.plaza=pl.id")
    public List<Eleccion> findByUsuarioAndConvocatoria(@Param("opositor") Usuario usuario, @Param("convocatoria") Convocatoria convocatoria);


    @Transactional
    public void deleteByPlaza(@Param("plaza") Plaza plaza);

}
