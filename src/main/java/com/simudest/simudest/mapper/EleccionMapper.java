package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.EleccionDto;
import com.simudest.simudest.entity.Eleccion;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EleccionMapper {

	public static EleccionDto eleccionToEleccionDto(Eleccion objOrigen){
		EleccionDto objDestino = new EleccionDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setPlazaDto(PlazaMapper.PlazaToPlazaDto(objOrigen.getPlaza()));
		objDestino.setUsuarioDto(UsuarioMapper.UsuarioToUsuarioDto(objOrigen.getUsuario()));
		return objDestino;
	}

	public static Eleccion eleccionDtoToEleccion(EleccionDto objOrigen){
		Eleccion objDestino = new Eleccion();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setPlaza(PlazaMapper.PlazaDtoToPlaza(objOrigen.getPlazaDto()));
		objDestino.setUsuario(UsuarioMapper.UsuarioDtoToUsuario(objOrigen.getUsuarioDto()));
		return objDestino;
	}

	public static List<EleccionDto> eleccionListToEleccionDtoList(List<Eleccion> listaOrigen){
		List<EleccionDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Eleccion objOrigen) -> listaDestino.add(eleccionToEleccionDto(objOrigen)));
		return listaDestino;
	}

	public static List<Eleccion> eleccionDtoListToEleccionList(List<EleccionDto> listaOrigen){
		List<Eleccion> listaDestino = new ArrayList<>();
		listaOrigen.forEach((EleccionDto objOrigen) -> listaDestino.add(eleccionDtoToEleccion(objOrigen)));
		return listaDestino;
	}

}
