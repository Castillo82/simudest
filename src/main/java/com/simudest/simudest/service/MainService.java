package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.exception.UsuarioNotFoundException;

import java.util.List;

public interface MainService {


    public List<ConvocatoriaDto> getMisConvocatorias(String idUser);

    public List<ConvocatoriaDto> getConvocatoriasActivas();

    public List<GrupoDto> getGrupos();

    public List<EspecialidadDto> getEspecialidades();

    public List<EspecialidadDto> getEspecialidadesbyGrupo(Integer grupoDto);

    public List<OrganismoDto> getOrganismos();

    public void guardarConvocatoria(ConvocatoriaDto convocatoriaDto) throws UsuarioNotFoundException,ConvocatoriaNotFoundException;
    
    public void solicitarAcceso(String idConvo, String idUsuario) throws UsuarioNotFoundException,ConvocatoriaNotFoundException;
    }
