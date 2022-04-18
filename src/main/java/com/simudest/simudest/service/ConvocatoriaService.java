package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.OpositorDto;
import com.simudest.simudest.dto.PlazaDto;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.exception.OpositorNotFoundException;
import com.simudest.simudest.exception.OrdenOpositorIncorrectoException;
import com.simudest.simudest.exception.UsuarioNotFoundException;

import java.util.List;

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

}
