package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.entity.Grupo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class GrupoMapper {

	public static GrupoDto grupoToGrupoDto(Grupo objOrigen){
		GrupoDto objDestino = new GrupoDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static Grupo grupoDtoToGrupo(GrupoDto objOrigen){
		Grupo objDestino = new Grupo();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static List<GrupoDto> grupoListToGrupoDtoList(List<Grupo> listaOrigen){
		List<GrupoDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Grupo objOrigen) -> listaDestino.add(grupoToGrupoDto(objOrigen)));
		return listaDestino;
	}

	public static List<Grupo> grupoDtoListToGrupoList(List<GrupoDto> listaOrigen){
		List<Grupo> listaDestino = new ArrayList<>();
		listaOrigen.forEach((GrupoDto objOrigen) -> listaDestino.add(grupoDtoToGrupo(objOrigen)));
		return listaDestino;
	}

}
