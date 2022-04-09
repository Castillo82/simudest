package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.entity.Organismo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrganismoMapper {

	public static OrganismoDto OrganismoToOrganismoDto(Organismo objOrigen){
		OrganismoDto objDestino = new OrganismoDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static Organismo OrganismoDtoToOrganismo(OrganismoDto objOrigen){
		Organismo objDestino = new Organismo();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static List<OrganismoDto> OrganismoListToOrganismoDtoList(List<Organismo> listaOrigen){
		List<OrganismoDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Organismo objOrigen) -> listaDestino.add(OrganismoToOrganismoDto(objOrigen)));
		return listaDestino;
	}

	public static List<Organismo> OrganismoDtoListToOrganismoList(List<OrganismoDto> listaOrigen){
		List<Organismo> listaDestino = new ArrayList<>();
		listaOrigen.forEach((OrganismoDto objOrigen) -> listaDestino.add(OrganismoDtoToOrganismo(objOrigen)));
		return listaDestino;
	}

}
