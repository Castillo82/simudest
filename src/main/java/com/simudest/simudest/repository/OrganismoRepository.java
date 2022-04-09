package com.simudest.simudest.repository;

import com.simudest.simudest.entity.Organismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismoRepository extends JpaRepository<Organismo, Integer> {

}
