package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.entity.Grupo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class GrupoMapper {

	public static GrupoDto GrupoToGrupoDto(Grupo objOrigen){
		GrupoDto objDestino = new GrupoDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static Grupo GrupoDtoToGrupo(GrupoDto objOrigen){
		Grupo objDestino = new Grupo();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static List<GrupoDto> GrupoListToGrupoDtoList(List<Grupo> listaOrigen){
		List<GrupoDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Grupo objOrigen) -> listaDestino.add(GrupoToGrupoDto(objOrigen)));
		return listaDestino;
	}

	public static List<Grupo> GrupoDtoListToGrupoList(List<GrupoDto> listaOrigen){
		List<Grupo> listaDestino = new ArrayList<>();
		listaOrigen.forEach((GrupoDto objOrigen) -> listaDestino.add(GrupoDtoToGrupo(objOrigen)));
		return listaDestino;
	}

}
