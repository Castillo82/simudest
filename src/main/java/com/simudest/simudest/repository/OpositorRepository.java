package com.simudest.simudest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simudest.simudest.entity.Opositor;

@Repository
public interface OpositorRepository extends JpaRepository<Opositor, Integer> {

}
