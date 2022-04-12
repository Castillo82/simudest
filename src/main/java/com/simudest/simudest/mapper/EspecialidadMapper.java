package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.entity.Especialidad;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadMapper {

	public static EspecialidadDto especialidadToEspecialidadDto(Especialidad objOrigen){
		EspecialidadDto objDestino = new EspecialidadDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setGrupoDto(GrupoMapper.grupoToGrupoDto(objOrigen.getGrupo()));
		return objDestino;
	}

	public static Especialidad especialidadDtoToEspecialidad(EspecialidadDto objOrigen){
		Especialidad objDestino = new Especialidad();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setGrupo(GrupoMapper.grupoDtoToGrupo(objOrigen.getGrupoDto()));
		return objDestino;
	}

	public static List<EspecialidadDto> especialidadListToEspecialidadDtoList(List<Especialidad> listaOrigen){
		List<EspecialidadDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Especialidad objOrigen) -> listaDestino.add(especialidadToEspecialidadDto(objOrigen)));
		return listaDestino;
	}

	public static List<Especialidad> especialidadDtoListToEspecialidadList(List<EspecialidadDto> listaOrigen){
		List<Especialidad> listaDestino = new ArrayList<>();
		listaOrigen.forEach((EspecialidadDto objOrigen) -> listaDestino.add(especialidadDtoToEspecialidad(objOrigen)));
		return listaDestino;
	}

}
