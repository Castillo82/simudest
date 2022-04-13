package com.simudest.simudest.dto;

import java.io.Serializable;

public class Alerta implements Serializable {

	private String titulo;

	private String texto;
	
	private String tipo;

	
	public Alerta(String titulo, String texto, String tipo) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
