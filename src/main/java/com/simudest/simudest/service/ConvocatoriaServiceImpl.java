package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.entity.Provincia;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.mapper.ConvocatoriaMapper;
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

	public ConvocatoriaDto getConvocatoria(String id) throws ConvocatoriaNotFoundException{
		Convocatoria convocatoria = convocatoriaRepository.findById(id).orElseThrow(ConvocatoriaNotFoundException::new);
		ConvocatoriaDto convocatoriaDto = ConvocatoriaMapper.convocatoriaToConvocatoriaDto(convocatoria);
		return convocatoriaDto;
	}

}
