package com.simudest.simudest.dto;

import com.simudest.simudest.entity.Especialidad;
import com.simudest.simudest.repository.OrganismoRepository;

import java.io.Serializable;
import java.util.Objects;

public class ConvocatoriaDto implements Serializable {

	private String id;

	private EspecialidadDto especialidadDto;

	private OrganismoDto organismoDto;

	private UsuarioDto usuarioDto;

	private String nombre;

	private String estado;

	private Integer nopositores;

	private Integer nplazas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EspecialidadDto getEspecialidadDto() {
		return especialidadDto;
	}

	public void setEspecialidadDto(EspecialidadDto especialidadDto) {
		this.especialidadDto = especialidadDto;
	}

	public OrganismoDto getOrganismoDto() {
		return organismoDto;
	}

	public void setOrganismoDto(OrganismoDto organismoDto) {
		this.organismoDto = organismoDto;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Integer getNopositores() {
		return nopositores;
	}

	public void setNopositores(Integer nopositores) {
		this.nopositores = nopositores;
	}

	public Integer getNplazas() {
		return nplazas;
	}

	public void setNplazas(Integer nplazas) {
		this.nplazas = nplazas;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConvocatoriaDto that = (ConvocatoriaDto) o;
		return Objects.equals(id, that.id) && Objects.equals(especialidadDto, that.especialidadDto) && Objects.equals(organismoDto, that.organismoDto) && Objects.equals(usuarioDto, that.usuarioDto) && Objects.equals(nombre, that.nombre) && Objects.equals(estado, that.estado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, especialidadDto, organismoDto, usuarioDto, nombre, estado);
	}
}
