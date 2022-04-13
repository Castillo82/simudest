package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioDto implements Serializable {

	private String id;

	private String email;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private Boolean admin;

	private String contra;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UsuarioDto that = (UsuarioDto) o;
		return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(admin, that.admin) && Objects.equals(contra, that.contra);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, nombre, apellido1, apellido2, admin, contra);
	}
}
