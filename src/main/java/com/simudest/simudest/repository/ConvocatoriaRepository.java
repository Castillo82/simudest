package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, String> {

    public List<Convocatoria> findByEstadoNot(String estado);

    @Query("SELECT co FROM Convocatoria co WHERE co.id=(:id) and co.estado!='OFF'")
    public Optional<Convocatoria> findByIdAndActiva(@Param("id") String id);

    //@Query("SELECT co FROM Convocatoria co WHERE co.usuario=(:usuario) and estado!='OFF'")
    //public List<Convocatoria> findByUser(@Param("idUser") Usuario usuario);
    public List<Convocatoria> findByUsuarioAndEstadoNot(Usuario usuario,String estado);

    @Query("SELECT co FROM Convocatoria co, Opositor op WHERE op.convocatoria=co and op.usuario=(:opositor) and op.validado=true and co.estado!='OFF'")
    public List<Convocatoria> findByOpositor(@Param("opositor") Usuario usuario);

}
