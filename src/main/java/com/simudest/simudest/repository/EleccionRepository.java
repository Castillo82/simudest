package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Eleccion;
import com.simudest.simudest.entity.EleccionId;
import com.simudest.simudest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

 /*
    @Query("SELECT el FROM Eleccion el, Plaza pl, Usuario us, Convocatoria co, Opositor op "+
           "WHERE pl.convocatoria=(:convocatoria) and el.usuario<>(:opositor) and op.orden<(:orden)"+
           "and el.plaza=pl.id and pl.convocatoria=co.id and op.convocatoria=co.id and op.usuario=us.id and el.usuario=us.id")
    public List<Eleccion> FindByNotUsuarioAndConvocatoriaOrden(@Param("opositor") Usuario usuario, @Param("convocatoria") Convocatoria convocatoria, @Param("orden") Integer orden);
*/


}
