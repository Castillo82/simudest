package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlazaRepository extends JpaRepository<Plaza, Integer> {

}
