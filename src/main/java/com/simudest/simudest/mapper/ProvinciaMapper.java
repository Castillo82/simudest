package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.ProvinciaDto;
import com.simudest.simudest.entity.Provincia;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProvinciaMapper {

	public static ProvinciaDto ProvinciaToProvinciaDto(Provincia objOrigen){
		ProvinciaDto objDestino = new ProvinciaDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static Provincia ProvinciaDtoToProvincia(ProvinciaDto objOrigen){
		Provincia objDestino = new Provincia();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static List<ProvinciaDto> ProvinciaListToProvinciaDtoList(List<Provincia> listaOrigen){
		List<ProvinciaDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Provincia objOrigen) -> listaDestino.add(ProvinciaToProvinciaDto(objOrigen)));
		return listaDestino;
	}

	public static List<Provincia> ProvinciaDtoListToProvinciaList(List<ProvinciaDto> listaOrigen){
		List<Provincia> listaDestino = new ArrayList<>();
		listaOrigen.forEach((ProvinciaDto objOrigen) -> listaDestino.add(ProvinciaDtoToProvincia(objOrigen)));
		return listaDestino;
	}

}
