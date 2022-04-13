package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class OpositorDto implements Serializable {

	private UsuarioDto usuarioDto;

	private ConvocatoriaDto convocatoriaDto;

	private Boolean validado;

	private Integer orden;

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public ConvocatoriaDto getConvocatoriaDto() {
		return convocatoriaDto;
	}

	public void setConvocatoriaDto(ConvocatoriaDto convocatoriaDto) {
		this.convocatoriaDto = convocatoriaDto;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OpositorDto that = (OpositorDto) o;
		return Objects.equals(usuarioDto, that.usuarioDto) && Objects.equals(convocatoriaDto, that.convocatoriaDto) && Objects.equals(validado, that.validado) && Objects.equals(orden, that.orden);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuarioDto, convocatoriaDto, validado, orden);
	}
}
