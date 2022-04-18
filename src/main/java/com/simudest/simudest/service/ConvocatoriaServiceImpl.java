package com.simudest.simudest.service;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.OpositorDto;
import com.simudest.simudest.dto.PlazaDto;
import com.simudest.simudest.entity.*;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.exception.OpositorNotFoundException;
import com.simudest.simudest.exception.OrdenOpositorIncorrectoException;
import com.simudest.simudest.exception.UsuarioNotFoundException;
import com.simudest.simudest.mapper.ConvocatoriaMapper;
import com.simudest.simudest.mapper.OpositorMapper;
import com.simudest.simudest.mapper.PlazaMapper;
import com.simudest.simudest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //  @Autowired
    //  private OpositorRepository opositorRepository;

    //  @Autowired
    //  private EleccionRepository eleccionRepository;

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

}

