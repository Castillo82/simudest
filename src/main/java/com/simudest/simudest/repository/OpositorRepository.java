package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.OpositorId;
import com.simudest.simudest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simudest.simudest.entity.Opositor;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpositorRepository extends JpaRepository<Opositor, OpositorId> {


    public Integer countByConvocatoria(Convocatoria convocatoria);

    public Optional<Opositor> findByUsuarioAndConvocatoriaAndValidado(Usuario usuario, Convocatoria convocatoria, Boolean Validado);

    public List<Opositor> findByConvocatoriaAndValidado(Convocatoria convocatoria, Boolean Validado);

    public List<Opositor> findByConvocatoriaAndOrden( Convocatoria convocatoria, Integer orden);

}
