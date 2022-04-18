package com.simudest.simudest.dto;

import java.io.Serializable;
import java.util.Objects;

public class PlazaDto implements Serializable {

	private Integer id;

	private ProvinciaDto provinciaDto;

	private ConvocatoriaDto convocatoriaDto;

	private String codigo;

	private String ministerio;

	private String centro;

	private String localidad;

	private String denominacion;

	private String nivel;

	private String c_especifico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProvinciaDto getProvinciaDto() {
		return provinciaDto;
	}

	public void setProvinciaDto(ProvinciaDto provinciaDto) {
		this.provinciaDto = provinciaDto;
	}

	public ConvocatoriaDto getConvocatoriaDto() {
		return convocatoriaDto;
	}

	public void setConvocatoriaDto(ConvocatoriaDto convocatoriaDto) {
		this.convocatoriaDto = convocatoriaDto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMinisterio() {
		return ministerio;
	}

	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getC_especifico() {
		return c_especifico;
	}

	public void setC_especifico(String c_especifico) {
		this.c_especifico = c_especifico;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PlazaDto plazaDto = (PlazaDto) o;
		return Objects.equals(id, plazaDto.id) && Objects.equals(provinciaDto, plazaDto.provinciaDto) && Objects.equals(convocatoriaDto, plazaDto.convocatoriaDto) && Objects.equals(codigo, plazaDto.codigo) && Objects.equals(ministerio, plazaDto.ministerio) && Objects.equals(centro, plazaDto.centro) && Objects.equals(localidad, plazaDto.localidad) && Objects.equals(denominacion, plazaDto.denominacion) && Objects.equals(nivel, plazaDto.nivel) && Objects.equals(c_especifico, plazaDto.c_especifico);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, provinciaDto, convocatoriaDto, codigo, ministerio, centro, localidad, denominacion, nivel, c_especifico);
	}
}
