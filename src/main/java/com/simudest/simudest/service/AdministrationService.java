package com.simudest.simudest.service;


import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.exception.GrupoNotFoundException;
import com.simudest.simudest.exception.OrganismoNotFoundException;

import java.util.List;

public interface AdministrationService {

    public List<OrganismoDto> getAllOrganismos();

    public OrganismoDto getOrganismoById(Integer idOrga) throws OrganismoNotFoundException;

    public void guardarOrganismo(OrganismoDto organismoDto);

    public void eliminarOrganismo(OrganismoDto organismoDto);

    public List<GrupoDto> getAllGrupos();

    public GrupoDto getGrupoById(Integer idGrupo) throws GrupoNotFoundException;

    public void guardarGrupo(GrupoDto grupoDto);

    public void eliminarGrupo(GrupoDto grupoDto);

}
