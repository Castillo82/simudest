package com.simudest.simudest.service;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.*;
import com.simudest.simudest.entity.*;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.exception.EspecialidadNotFoundException;
import com.simudest.simudest.exception.OrganismoNotFoundException;
import com.simudest.simudest.exception.UsuarioNotFoundException;
import com.simudest.simudest.mapper.*;
import com.simudest.simudest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private OrganismoRepository organismoRepository;

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OpositorRepository opositorRepository;

    public UsuarioDto getUsuariobyId(String id){
        Usuario usuario = usuarioRepository.findById(id).get();
        return UsuarioMapper.UsuarioToUsuarioDto(usuario);
    }

    public Set<ConvocatoriaDto> getMisConvocatorias(String idUser){
        Usuario usuario = usuarioRepository.findById(idUser).get();
        List <Convocatoria> dbConvocatorias = convocatoriaRepository.findByUsuarioAndEstadoNot(usuario, Constantes.CONVOCATORIA_ESTADO_INACTIVA);
        dbConvocatorias.addAll(convocatoriaRepository.findByOpositor(usuario));
        //List <Convocatoria> dbConvocatorias = convocatoriaRepository.findByOpositor(usuario);
        List <ConvocatoriaDto> convocatoriasDto =  ConvocatoriaMapper.convocatoriaListToConvocatoriaDtoList(dbConvocatorias);
        return new HashSet<ConvocatoriaDto>(convocatoriasDto);
    }

    public List<ConvocatoriaDto> getConvocatoriasActivas(){
        List <Convocatoria> dbConvocatorias = convocatoriaRepository.findByEstadoNot(Constantes.CONVOCATORIA_ESTADO_INACTIVA);
        return ConvocatoriaMapper.convocatoriaListToConvocatoriaDtoList(dbConvocatorias);
    }

    public List<GrupoDto> getGrupos(){
        List <Grupo> dbGrupos = grupoRepository.findAll();
        return GrupoMapper.grupoListToGrupoDtoList(dbGrupos);
    }

    public List<EspecialidadDto> getEspecialidades(){
        List <Especialidad> dbEspecialidades = especialidadRepository.findAll();
        return EspecialidadMapper.especialidadListToEspecialidadDtoList(dbEspecialidades);
    }

    public List<EspecialidadDto> getEspecialidadesbyGrupo(Integer grupo){
        Grupo objGrupo = grupoRepository.getById(grupo);
        List <Especialidad> dbEspecialidades = especialidadRepository.findByGrupo(objGrupo);
        return EspecialidadMapper.especialidadListToEspecialidadDtoList(dbEspecialidades);
    }

    public List<OrganismoDto> getOrganismos(){
        List <Organismo> dbOrganismos = organismoRepository.findAll();
        return OrganismoMapper.organismoListToOrganismoDtoList(dbOrganismos);
    }

    public void guardarConvocatoria(ConvocatoriaDto convocatoriaDto) throws UsuarioNotFoundException,ConvocatoriaNotFoundException{
    	Convocatoria convocatoria = ConvocatoriaMapper.convocatoriaDtoToConvocatoria(convocatoriaDto);
        boolean esNueva = convocatoria.getId() == null;
    	convocatoriaRepository.save(convocatoria);
        if (esNueva){
            solicitarAcceso(convocatoria.getId(), convocatoria.getUsuario().getId());
        }
    	
    }

    public void solicitarAcceso(String idConvo, String idUsuario) throws UsuarioNotFoundException,ConvocatoriaNotFoundException {
    	Opositor opositor = new Opositor();
    	Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
    	Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
    	opositor.setConvocatoria(convocatoria);
    	opositor.setUsuario(usuario);
    	opositor.setValidado(false);
        OpositorId opositorId = new OpositorId(usuario.getId(), convocatoria.getId());
        opositor.setId(opositorId);
    	opositorRepository.save(opositor);
    }

    public EspecialidadDto getEspecialidadbyId(Integer id) throws EspecialidadNotFoundException{
        Especialidad especialidad = especialidadRepository.findById(id).orElseThrow(EspecialidadNotFoundException::new);
        return EspecialidadMapper.especialidadToEspecialidadDto(especialidad);
    }

    public OrganismoDto getOrganismobyId(Integer id) throws OrganismoNotFoundException{
        Organismo organismo = organismoRepository.findById(id).orElseThrow(OrganismoNotFoundException::new);
        return OrganismoMapper.organismoToOrganismoDto(organismo);

    }

}
