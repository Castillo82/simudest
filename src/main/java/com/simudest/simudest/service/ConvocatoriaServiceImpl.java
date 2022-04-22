package com.simudest.simudest.service;

import com.simudest.simudest.dto.*;
import com.simudest.simudest.entity.*;
import com.simudest.simudest.exception.*;
import com.simudest.simudest.mapper.*;
import com.simudest.simudest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConvocatoriaServiceImpl implements ConvocatoriaService {


    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private PlazaRepository plazaRepository;

    @Autowired
    private OpositorRepository opositorRepository;

    @Autowired
    private EleccionRepository eleccionRepository;

	public ConvocatoriaDto getConvocatoria(String id) throws ConvocatoriaNotFoundException{
		Convocatoria convocatoria = convocatoriaRepository.findById(id).orElseThrow(ConvocatoriaNotFoundException::new);
		ConvocatoriaDto convocatoriaDto = ConvocatoriaMapper.convocatoriaToConvocatoriaDto(convocatoria);
		return convocatoriaDto;
	}

    public Integer getConvocatoriaNopositoresActual(ConvocatoriaDto convocatoriaDto){
        Convocatoria convocatoria = ConvocatoriaMapper.convocatoriaDtoToConvocatoria(convocatoriaDto);
        return opositorRepository.countByConvocatoria(convocatoria);
    }

    public Integer getConvocatoriaNPlazasActual(ConvocatoriaDto convocatoriaDto){
        Convocatoria convocatoria = ConvocatoriaMapper.convocatoriaDtoToConvocatoria(convocatoriaDto);
        return plazaRepository.countByConvocatoria(convocatoria);
    }

    public Boolean puedeConsultarConvocatoria(String idUsuario, String idConvo) throws UsuarioNotFoundException,ConvocatoriaNotFoundException{
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        if (convocatoria.getUsuario().getId().equals(usuario.getId())){
            return true;
        }else if (opositorRepository.findByUsuarioAndConvocatoriaAndValidado(usuario, convocatoria, true).isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public Boolean puedeAdministrarConvocatoria(String idUsuario, String idConvo) throws UsuarioNotFoundException,ConvocatoriaNotFoundException{
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        return convocatoria.getUsuario().getId().equals(usuario.getId()) || usuario.getAdmin();
    }


    public List<OpositorDto> getOpositoresConvocatoria(String idConvo, Boolean validado) throws ConvocatoriaNotFoundException{
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        List<Opositor> opositores = opositorRepository.findByConvocatoriaAndValidado(convocatoria, validado);
        return OpositorMapper.opositorListToOpositorDtoList(opositores);
    }

    public void validarOpositor (String idUsuario, String idConvo, Integer orden) throws OpositorNotFoundException, OrdenOpositorIncorrectoException {
        Opositor opositor = opositorRepository.findById(new OpositorId(idUsuario, idConvo)).orElseThrow(OpositorNotFoundException::new);
        if(!opositorRepository.findByOrden(orden).isEmpty()) {
            throw new OrdenOpositorIncorrectoException();
        }else{
            opositor.setOrden(orden);
            opositor.setValidado(true);
            opositorRepository.save(opositor);
        }
    }

    public void rechazarOpositor (String idUsuario, String idConvo) throws OpositorNotFoundException{
        Opositor opositor = opositorRepository.findById(new OpositorId(idUsuario, idConvo)).orElseThrow(OpositorNotFoundException::new);
        opositorRepository.delete(opositor);
    }

    public List<PlazaDto> getPlazasConvocatoria(String idConvo) throws ConvocatoriaNotFoundException{
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        List<Plaza> plazas = plazaRepository.findByConvocatoria(convocatoria);
        return PlazaMapper.PlazaListToPlazaDtoList(plazas);

    }

    public PlazaDto getPlazabyId(String idPlaza) throws PlazaNotFoundException{
        Plaza plaza = plazaRepository.findById(idPlaza).orElseThrow(PlazaNotFoundException::new);
        return PlazaMapper.PlazaToPlazaDto(plaza);

    }

    public List<ProvinciaDto> getProvincias(){
        return ProvinciaMapper.ProvinciaListToProvinciaDtoList(provinciaRepository.findAll());
    }

    public ProvinciaDto getProvincia(Integer id) throws ProvinciaNotFoundException{
        return ProvinciaMapper.ProvinciaToProvinciaDto(provinciaRepository.findById(id).orElseThrow(ProvinciaNotFoundException::new));
    }

    public void guardarPlaza(PlazaDto plazaDto){
        Plaza plaza = PlazaMapper.PlazaDtoToPlaza(plazaDto);
        plazaRepository.save(plaza);
    }

    public void borrarPlaza(PlazaDto plazaDto){
        Plaza plaza = PlazaMapper.PlazaDtoToPlaza(plazaDto);
        plazaRepository.delete(plaza);
    }

    // Al seleccionar una plaza la buscamos por orden y usuario, si existe se borra
    public void seleccionarPlaza(PlazaDto plazaDto, String idUsuario, Integer orden) throws UsuarioNotFoundException{
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
        Plaza plaza = PlazaMapper.PlazaDtoToPlaza(plazaDto);
        Optional<Eleccion> elecciondb = eleccionRepository.findByUsuarioAndOrden(usuario, orden);
        if (elecciondb.isPresent()){
            eleccionRepository.delete(elecciondb.get());
        }
        Eleccion eleccion = new Eleccion();
        eleccion.setId(new EleccionId(idUsuario, plazaDto.getId()));
        eleccion.setPlaza(plaza);
        eleccion.setUsuario(usuario);
        eleccion.setOrden(orden);
        eleccionRepository.save(eleccion);
    }

    public Map<Integer, EleccionDto> getMapElecciones(String idUsuario, String idConvo) throws UsuarioNotFoundException, ConvocatoriaNotFoundException{
        Map <Integer, EleccionDto> retorno = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        List<Eleccion> elecciones = eleccionRepository.findByUsuario(usuario);
        //Se rellenan las elecciones existentes del opositor en esta convocatoria
        for (Eleccion eleccion : elecciones){
            if (eleccion.getPlaza().getConvocatoria().getId().equals(idConvo)){
                retorno.put(eleccion.getOrden(), EleccionMapper.eleccionToEleccionDto(eleccion));
            }
        }
        //Se rellenan el resto de ordenes vacios
        for (int i=1; i<convocatoria.getNopositores()+1;i++){
            if (retorno.get(new Integer(i)) == null){
                retorno.put(new Integer(i), new EleccionDto());
            }
        }
        return retorno;
    }

    public List<EleccionDto> getElecciones(String idUsuario, String idConvo) throws UsuarioNotFoundException, ConvocatoriaNotFoundException{
        //TODO si me quedo este metodo hace falta refactor, se puede hacer solo con la query sin iterar
        List<EleccionDto> retorno = new ArrayList<>();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);
        Convocatoria convocatoria = convocatoriaRepository.findById(idConvo).orElseThrow(ConvocatoriaNotFoundException::new);
        List<Eleccion> elecciones = eleccionRepository.findByUsuario(usuario);
        //Se rellenan las elecciones existentes del opositor en esta convocatoria
        for (Eleccion eleccion : elecciones){
            if (eleccion.getPlaza().getConvocatoria().getId().equals(idConvo)){
                retorno.add(EleccionMapper.eleccionToEleccionDto(eleccion));
            }
        }
        return retorno;
    }

}

