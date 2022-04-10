package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.entity.Grupo;

import java.util.List;

public interface MainService {

    public List<ConvocatoriaDto> getConvocatoriasActivas();

    public List<GrupoDto> getGrupos();

    public List<EspecialidadDto> getEspecialidades();

    public List<EspecialidadDto> getEspecialidadesbyGrupo(Integer grupoDto);

    public List<OrganismoDto> getOrganismos();

    }
