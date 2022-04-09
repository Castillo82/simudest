package com.simudest.simudest.service;

import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.entity.Provincia;
import com.simudest.simudest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvocatoriaServiceImpl implements ConvocatoriaService {


    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private PlazaRepository plazaRepository;

    //  @Autowired
    //  private OpositorRepository opositorRepository;

    //  @Autowired
    //  private EleccionRepository eleccionRepository;


}
