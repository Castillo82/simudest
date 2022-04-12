package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.OpositorDto;
import com.simudest.simudest.entity.Opositor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OpositorMapper {

	public static OpositorDto opositorToOpositorDto(Opositor objOrigen){
		OpositorDto objDestino = new OpositorDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setConvocatoriaDto(ConvocatoriaMapper.convocatoriaToConvocatoriaDto(objOrigen.getConvocatoria()));
		objDestino.setUsuarioDto(UsuarioMapper.UsuarioToUsuarioDto(objOrigen.getUsuario()));
		return objDestino;
	}

	public static Opositor opositorDtoToOpositor(OpositorDto objOrigen){
		Opositor objDestino = new Opositor();
		BeanUtils.copyProperties(objOrigen , objDestino);
		objDestino.setConvocatoria(ConvocatoriaMapper.convocatoriaDtoToConvocatoria(objOrigen.getConvocatoriaDto()));
		objDestino.setUsuario(UsuarioMapper.UsuarioDtoToUsuario(objOrigen.getUsuarioDto()));
		return objDestino;
	}

	public static List<OpositorDto> opositorListToOpositorDtoList(List<Opositor> listaOrigen){
		List<OpositorDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Opositor objOrigen) -> listaDestino.add(opositorToOpositorDto(objOrigen)));
		return listaDestino;
	}

	public static List<Opositor> opositorDtoListToOpositorList(List<OpositorDto> listaOrigen){
		List<Opositor> listaDestino = new ArrayList<>();
		listaOrigen.forEach((OpositorDto objOrigen) -> listaDestino.add(opositorDtoToOpositor(objOrigen)));
		return listaDestino;
	}

}
