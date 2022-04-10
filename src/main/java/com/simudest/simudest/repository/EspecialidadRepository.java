package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Especialidad;
import com.simudest.simudest.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    public List<Especialidad> findByGrupo(Grupo grupo);

}
