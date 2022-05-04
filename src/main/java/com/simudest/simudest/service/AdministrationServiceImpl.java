package com.simudest.simudest.service;

import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.GrupoDto;
import com.simudest.simudest.dto.OrganismoDto;
import com.simudest.simudest.entity.Especialidad;
import com.simudest.simudest.entity.Grupo;
import com.simudest.simudest.entity.Organismo;
import com.simudest.simudest.exception.EspecialidadNotFoundException;
import com.simudest.simudest.exception.GrupoNotFoundException;
import com.simudest.simudest.exception.OrganismoNotFoundException;
import com.simudest.simudest.mapper.EspecialidadMapper;
import com.simudest.simudest.mapper.GrupoMapper;
import com.simudest.simudest.mapper.OrganismoMapper;
import com.simudest.simudest.repository.EspecialidadRepository;
import com.simudest.simudest.repository.GrupoRepository;
import com.simudest.simudest.repository.OrganismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private OrganismoRepository organismoRepository;

    @Override
    public List<OrganismoDto> getAllOrganismos(){
        return OrganismoMapper.organismoListToOrganismoDtoList(organismoRepository.findAll());
    }

    public OrganismoDto getOrganismoById(Integer idOrga) throws OrganismoNotFoundException{
        Organismo organismo = organismoRepository.findById(idOrga).orElseThrow(OrganismoNotFoundException::new);
        return OrganismoMapper.organismoToOrganismoDto(organismo);
    }

    public void guardarOrganismo(OrganismoDto organismoDto){
        Organismo organismo = OrganismoMapper.organismoDtoToOrganismo(organismoDto);
        organismoRepository.save(organismo);
    }

    public void eliminarOrganismo(OrganismoDto organismoDto){
        Organismo organismo = OrganismoMapper.organismoDtoToOrganismo(organismoDto);
        organismoRepository.delete(organismo);
    }

    @Override
    public List<GrupoDto> getAllGrupos(){
        return GrupoMapper.grupoListToGrupoDtoList(grupoRepository.findAll());
    }

    public GrupoDto getGrupoById(Integer idGrupo) throws GrupoNotFoundException{
        Grupo grupo = grupoRepository.findById(idGrupo).orElseThrow(GrupoNotFoundException::new);
        return GrupoMapper.grupoToGrupoDto(grupo);
    }

    public void guardarGrupo(GrupoDto grupoDto){
        Grupo grupo = GrupoMapper.grupoDtoToGrupo(grupoDto);
        grupoRepository.save(grupo);
    }

    public void eliminarGrupo(GrupoDto grupoDto){
        Grupo grupo = GrupoMapper.grupoDtoToGrupo(grupoDto);
        grupoRepository.delete(grupo);
    }

    @Override
    public List<EspecialidadDto> getAllEspecialidades(){
        return EspecialidadMapper.especialidadListToEspecialidadDtoList(especialidadRepository.findAll());
    }

    public EspecialidadDto getEspecialidadById(Integer idEspecialidad) throws EspecialidadNotFoundException {
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad).orElseThrow(EspecialidadNotFoundException::new);
        return EspecialidadMapper.especialidadToEspecialidadDto(especialidad);
    }

    public void guardarEspecialidad(EspecialidadDto especialidadDto){
        Especialidad especialidad = EspecialidadMapper.especialidadDtoToEspecialidad(especialidadDto);
        especialidadRepository.save(especialidad);
    }

    public void eliminarEspecialidad(EspecialidadDto especialidadDto){
        Especialidad especialidad = EspecialidadMapper.especialidadDtoToEspecialidad(especialidadDto);
        especialidadRepository.delete(especialidad);
    }

}
