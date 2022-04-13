package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class EspecialidadDto implements Serializable {

	private Integer id;

	private GrupoDto grupoDto;

	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GrupoDto getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDto grupoDto) {
		this.grupoDto = grupoDto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EspecialidadDto that = (EspecialidadDto) o;
		return Objects.equals(id, that.id) && Objects.equals(grupoDto, that.grupoDto) && Objects.equals(nombre, that.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, grupoDto, nombre);
	}
}
