package com.simudest.simudest.service;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.entity.Convocatoria;
import com.simudest.simudest.entity.Especialidad;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.entity.Organismo;
import com.simudest.simudest.mapper.ConvocatoriaMapper;
import com.simudest.simudest.mapper.EspecialidadMapper;
import com.simudest.simudest.mapper.GrupoMapper;
import com.simudest.simudest.mapper.OrganismoMapper;
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

    public List<GrupoDto> getGrupos(){
        List <Grupo> dbGrupos = grupoRepository.findAll();
        return GrupoMapper.grupoListToGrupoDtoList(dbGrupos);
    }

    public List<EspecialidadDto> getEspecialidades(){
        List <Especialidad> dbEspecialidades = especialidadRepository.findAll();
        return EspecialidadMapper.especialidadListToEspecialidadDtoList(dbEspecialidades);
    }

    public List<EspecialidadDto> getEspecialidadesbyGrupo(Integer grupo){
        Grupo objGrupo = grupoRepository.getById(grupo);
        List <Especialidad> dbEspecialidades = especialidadRepository.findByGrupo(objGrupo);
        return EspecialidadMapper.especialidadListToEspecialidadDtoList(dbEspecialidades);
    }

    public List<OrganismoDto> getOrganismos(){
        List <Organismo> dbOrganismos = organismoRepository.findAll();
        return OrganismoMapper.organismoListToOrganismoDtoList(dbOrganismos);
    }

    public void guardarConvocatoria(ConvocatoriaDto convocatoriaDto) {
    	Convocatoria convocatoria = ConvocatoriaMapper.convocatoriaDtoToConvocatoria(convocatoriaDto);
    	convocatoriaRepository.save(convocatoria);
    	
    }


}
