package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class EleccionDto implements Serializable {

	private UsuarioDto usuarioDto;

	private PlazaDto plazaDto;

	private Integer orden;

	private String color;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EleccionDto that = (EleccionDto) o;
		return Objects.equals(usuarioDto, that.usuarioDto) && Objects.equals(plazaDto, that.plazaDto) && Objects.equals(orden, that.orden);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuarioDto, plazaDto, orden);
	}

}
