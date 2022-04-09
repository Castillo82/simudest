package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.entity.Especialidad;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadMapper {

	public static EspecialidadDto EspecialidadToEspecialidadDto(Especialidad objOrigen){
		EspecialidadDto objDestino = new EspecialidadDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setGrupoDto(GrupoMapper.GrupoToGrupoDto(objOrigen.getGrupo()));
		return objDestino;
	}

	public static Especialidad EspecialidadDtoToEspecialidad(EspecialidadDto objOrigen){
		Especialidad objDestino = new Especialidad();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setGrupo(GrupoMapper.GrupoDtoToGrupo(objOrigen.getGrupoDto()));
		return objDestino;
	}

	public static List<EspecialidadDto> EspecialidadListToEspecialidadDtoList(List<Especialidad> listaOrigen){
		List<EspecialidadDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Especialidad objOrigen) -> listaDestino.add(EspecialidadToEspecialidadDto(objOrigen)));
		return listaDestino;
	}

	public static List<Especialidad> EspecialidadDtoListToEspecialidadList(List<EspecialidadDto> listaOrigen){
		List<Especialidad> listaDestino = new ArrayList<>();
		listaOrigen.forEach((EspecialidadDto objOrigen) -> listaDestino.add(EspecialidadDtoToEspecialidad(objOrigen)));
		return listaDestino;
	}

}
