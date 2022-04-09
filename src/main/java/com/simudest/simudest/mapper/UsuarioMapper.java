package com.simudest.simudest.mapper;


import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.entity.Usuario;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

	public static UsuarioDto UsuarioToUsuarioDto(Usuario objOrigen){
		UsuarioDto objDestino = new UsuarioDto();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static Usuario UsuarioDtoToUsuario(UsuarioDto objOrigen){
		Usuario objDestino = new Usuario();
		BeanUtils.copyProperties(objOrigen , objDestino);
		return objDestino;
	}

	public static List<UsuarioDto> UsuarioListToUsuarioDtoList(List<Usuario> listaOrigen){
		List<UsuarioDto> listaDestino = new ArrayList<>();
		listaOrigen.forEach((Usuario objOrigen) -> listaDestino.add(UsuarioToUsuarioDto(objOrigen)));
		return listaDestino;
	}

	public static List<Usuario> UsuarioDtoListToUsuarioList(List<UsuarioDto> listaOrigen){
		List<Usuario> listaDestino = new ArrayList<>();
		listaOrigen.forEach((UsuarioDto objOrigen) -> listaDestino.add(UsuarioDtoToUsuario(objOrigen)));
		return listaDestino;
	}

}
