package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.OpositorDto;
import com.simudest.simudest.entity.Opositor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OpositorMapper {

	public static OpositorDto OpositorToOpositorDto(Opositor objOrigen){
		OpositorDto objDestino = new OpositorDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setConvocatoriaDto(ConvocatoriaMapper.convocatoriaToConvocatoriaDto(objOrigen.getConvocatoria()));
		objDestino.setUsuarioDto(UsuarioMapper.UsuarioToUsuarioDto(objOrigen.getUsuario()));
		return objDestino;
	}

	public static Opositor OpositorDtoToOpositor(OpositorDto objOrigen){
		Opositor objDestino = new Opositor();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setConvocatoria(ConvocatoriaMapper.convocatoriaDtoToConvocatoria(objOrigen.getConvocatoriaDto()));
		objDestino.setUsuario(UsuarioMapper.UsuarioDtoToUsuario(objOrigen.getUsuarioDto()));
		return objDestino;
	}

	public static List<OpositorDto> OpositorListToOpositorDtoList(List<Opositor> listaOrigen){
		List<OpositorDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Opositor objOrigen) -> listaDestino.add(OpositorToOpositorDto(objOrigen)));
		return listaDestino;
	}

	public static List<Opositor> OpositorDtoListToOpositorList(List<OpositorDto> listaOrigen){
		List<Opositor> listaDestino = new ArrayList<>();
		listaOrigen.forEach((OpositorDto objOrigen) -> listaDestino.add(OpositorDtoToOpositor(objOrigen)));
		return listaDestino;
	}

}
