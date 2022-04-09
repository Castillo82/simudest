package com.simudest.simudest.dto;

import java.io.Serializable;

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
}
