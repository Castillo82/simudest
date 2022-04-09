package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.PlazaDto;
import com.simudest.simudest.entity.Plaza;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PlazaMapper {

	public static PlazaDto PlazaToPlazaDto(Plaza objOrigen){
		PlazaDto objDestino = new PlazaDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setProvinciaDto(ProvinciaMapper.ProvinciaToProvinciaDto(objOrigen.getProvincia()));
		objDestino.setConvocatoriaDto(ConvocatoriaMapper.convocatoriaToConvocatoriaDto(objOrigen.getConvocatoria()));
		return objDestino;
	}

	public static Plaza PlazaDtoToPlaza(PlazaDto objOrigen){
		Plaza objDestino = new Plaza();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setProvincia(ProvinciaMapper.ProvinciaDtoToProvincia(objOrigen.getProvinciaDto()));
		objDestino.setConvocatoria(ConvocatoriaMapper.convocatoriaDtoToConvocatoria(objOrigen.getConvocatoriaDto()));
		return objDestino;
	}

	public static List<PlazaDto> PlazaListToPlazaDtoList(List<Plaza> listaOrigen){
		List<PlazaDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Plaza objOrigen) -> listaDestino.add(PlazaToPlazaDto(objOrigen)));
		return listaDestino;
	}

	public static List<Plaza> PlazaDtoListToPlazaList(List<PlazaDto> listaOrigen){
		List<Plaza> listaDestino = new ArrayList<>();
		listaOrigen.forEach((PlazaDto objOrigen) -> listaDestino.add(PlazaDtoToPlaza(objOrigen)));
		return listaDestino;
	}

}
