package com.simudest.simudest.service;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.entity.Grupo;

import java.util.List;

public interface MainService {

    public List<ConvocatoriaDto> getConvocatoriasActivas();

}
