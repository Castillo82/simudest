package com.simudest.simudest.service;

import com.simudest.simudest.dto.*;
import com.simudest.simudest.exception.*;

import java.util.List;
import java.util.Set;

public interface MainService {

    public UsuarioDto getUsuariobyId(String id);

    public Set<ConvocatoriaDto> getMisConvocatorias(String idUser);

    public List<ConvocatoriaDto> getConvocatoriasActivas();

    public List<GrupoDto> getGrupos();

    public List<EspecialidadDto> getEspecialidades();

    public List<EspecialidadDto> getEspecialidadesbyGrupo(Integer grupoDto);

    public List<OrganismoDto> getOrganismos();

    public void guardarConvocatoria(ConvocatoriaDto convocatoriaDto) throws UsuarioNotFoundException,ConvocatoriaNotFoundException;

    public void eliminarConvocatoria(String idConvo, String idUsuario) throws UsuarioNotFoundException,ConvocatoriaNotFoundException, SinPermisoException;

    public void solicitarAcceso(String idConvo, String idUsuario, String palabra) throws UsuarioNotFoundException,ConvocatoriaNotFoundException, OpositorAlreadyExistException, PalabraIncorrectaException;

    public EspecialidadDto getEspecialidadbyId(Integer id) throws EspecialidadNotFoundException;

    public OrganismoDto getOrganismobyId(Integer id) throws OrganismoNotFoundException;

    public ConvocatoriaDto getConvocatoria(String id) throws ConvocatoriaNotFoundException;

    }
