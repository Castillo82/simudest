package com.simudest.simudest.service;

import com.simudest.simudest.entity.Especialidad;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.repository.EspecialidadRepository;
import com.simudest.simudest.repository.GrupoRepository;
import com.simudest.simudest.repository.OrganismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private OrganismoRepository organismoRepository;

    @Override
    public List<Grupo> getAllGrupos(){
        return grupoRepository.findAll();
    }

}
