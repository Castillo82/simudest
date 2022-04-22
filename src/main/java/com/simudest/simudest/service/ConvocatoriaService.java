package com.simudest.simudest.service;

import com.simudest.simudest.dto.*;
import com.simudest.simudest.exception.*;

import java.util.List;
import java.util.Map;

public interface ConvocatoriaService {
	
	public ConvocatoriaDto getConvocatoria(String id) throws ConvocatoriaNotFoundException;

	public Integer getConvocatoriaNopositoresActual(ConvocatoriaDto convocatoriaDto);

	public Integer getConvocatoriaNPlazasActual(ConvocatoriaDto convocatoriaDto);

	public Boolean puedeConsultarConvocatoria(String idUsuario, String idConvo) throws UsuarioNotFoundException,ConvocatoriaNotFoundException;

	public Boolean puedeAdministrarConvocatoria(String idUsuario, String idConvo) throws UsuarioNotFoundException,ConvocatoriaNotFoundException;

	public List<OpositorDto> getOpositoresConvocatoria(String idConvo, Boolean validado) throws ConvocatoriaNotFoundException;

	public void validarOpositor (String idUsuario, String idConvo, Integer orden) throws OpositorNotFoundException, OrdenOpositorIncorrectoException;

	public void rechazarOpositor (String idUsuario, String idConvo) throws OpositorNotFoundException;

	public List<PlazaDto> getPlazasConvocatoria(String idConvo) throws ConvocatoriaNotFoundException;

	public PlazaDto getPlazabyId(String idPlaza) throws PlazaNotFoundException;

	public List<ProvinciaDto> getProvincias();

	public ProvinciaDto getProvincia(Integer id) throws ProvinciaNotFoundException;

	public void guardarPlaza(PlazaDto plazaDto);

	public void borrarPlaza(PlazaDto plazaDto);

	public void seleccionarPlaza(PlazaDto plazaDto, String idUsuario, Integer orden) throws UsuarioNotFoundException;

	public Map<Integer, EleccionDto> getMapElecciones(String idUsuario, String idConvo) throws UsuarioNotFoundException, ConvocatoriaNotFoundException;

	public List<EleccionDto> getElecciones(String idUsuario, String idConvo) throws UsuarioNotFoundException, ConvocatoriaNotFoundException;

	}
