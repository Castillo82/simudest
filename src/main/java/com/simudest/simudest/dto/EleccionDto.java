package com.simudest.simudest.dto;

import java.io.Serializable;

public class EleccionDto implements Serializable {

	private UsuarioDto usuarioDto;

	private PlazaDto plazaDto;

	private Integer orden;

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public PlazaDto getPlazaDto() {
		return plazaDto;
	}

	public void setPlazaDto(PlazaDto plazaDto) {
		this.plazaDto = plazaDto;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
