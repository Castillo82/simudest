package com.simudest.simudest.service;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.mapper.ConvocatoriaMapper;
import com.simudest.simudest.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private OrganismoRepository organismoRepository;

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<ConvocatoriaDto> getConvocatoriasActivas(){
        List <Convocatoria> dbConvocatorias = convocatoriaRepository.findByEstadoNot(Constantes.CONVOCATORIA_ESTADO_INACTIVA);
        return ConvocatoriaMapper.convocatoriaListToConvocatoriaDtoList(dbConvocatorias);
    }




}
