package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlazaRepository extends JpaRepository<Plaza, Integer> {

    public Integer countByConvocatoria(Convocatoria convocatoria);

    public List<Plaza> findByConvocatoria(Convocatoria convocatoria);
}
