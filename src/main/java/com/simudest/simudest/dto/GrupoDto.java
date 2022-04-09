package com.simudest.simudest.dto;

import javax.persistence.*;
import java.io.Serializable;

public class GrupoDto implements Serializable {

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
}
