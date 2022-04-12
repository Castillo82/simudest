package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.entity.Convocatoria;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvocatoriaMapper {

	public static ConvocatoriaDto convocatoriaToConvocatoriaDto(Convocatoria objOrigen){
		ConvocatoriaDto objDestino = new ConvocatoriaDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setEspecialidadDto(EspecialidadMapper.especialidadToEspecialidadDto(objOrigen.getEspecialidad()));
		objDestino.setOrganismoDto(OrganismoMapper.organismoToOrganismoDto(objOrigen.getOrganismo()));
		objDestino.setUsuarioDto(UsuarioMapper.UsuarioToUsuarioDto(objOrigen.getUsuario()));
		return objDestino;
	}

	public static Convocatoria convocatoriaDtoToConvocatoria(ConvocatoriaDto objOrigen){
		Convocatoria objDestino = new Convocatoria();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setEspecialidad(EspecialidadMapper.especialidadDtoToEspecialidad(objOrigen.getEspecialidadDto()));
		objDestino.setOrganismo(OrganismoMapper.organismoDtoToOrganismo(objOrigen.getOrganismoDto()));
		objDestino.setUsuario(UsuarioMapper.UsuarioDtoToUsuario(objOrigen.getUsuarioDto()));
		return objDestino;
	}

	public static List<ConvocatoriaDto> convocatoriaListToConvocatoriaDtoList(List<Convocatoria> listaOrigen){
		List<ConvocatoriaDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Convocatoria objOrigen) -> listaDestino.add(convocatoriaToConvocatoriaDto(objOrigen)));
		return listaDestino;
	}

	public static List<Convocatoria> convocatoriaDtoListToConvocatoriaList(List<ConvocatoriaDto> listaOrigen){
		List<Convocatoria> listaDestino = new ArrayList<>();
		listaOrigen.forEach((ConvocatoriaDto objOrigen) -> listaDestino.add(convocatoriaDtoToConvocatoria(objOrigen)));
		return listaDestino;
	}

}
