package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class OrganismoDto implements Serializable {

	private Integer id;

	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		OrganismoDto that = (OrganismoDto) o;
		return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
}
