package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.entity.Provincia;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;

import java.util.List;

public interface ConvocatoriaService {
	
	public ConvocatoriaDto getConvocatoria(String id) throws ConvocatoriaNotFoundException;

	public Integer getConvocatoriaNopositoresActual(ConvocatoriaDto convocatoriaDto);

	public Integer getConvocatoriaNPlazasActual(ConvocatoriaDto convocatoriaDto);

}
