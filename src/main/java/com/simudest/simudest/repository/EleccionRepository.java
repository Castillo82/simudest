package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Eleccion;
import com.simudest.simudest.entity.EleccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleccionRepository extends JpaRepository<Eleccion, EleccionId> {

}
