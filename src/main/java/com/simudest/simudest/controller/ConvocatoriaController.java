package com.simudest.simudest.controller;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.OpositorDto;
import com.simudest.simudest.exception.OpositorNotFoundException;
import com.simudest.simudest.exception.OrdenOpositorIncorrectoException;
import com.simudest.simudest.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.Alerta;
import com.simudest.simudest.exception.ConvocatoriaNotFoundException;
import com.simudest.simudest.service.ConvocatoriaService;

@Controller
@RequestMapping("/convocatoria")
public class ConvocatoriaController {

	@Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping("")
    public ModelAndView convocatoria(String id, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), id)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            ConvocatoriaDto convocatoria = convocatoriaService.getConvocatoria(id);
            mav.addObject("convocatoria", convocatoria);
            mav.addObject("nopositoresActual", convocatoriaService.getConvocatoriaNopositoresActual(convocatoria));
            mav.addObject("nplazasActual", convocatoriaService.getConvocatoriaNPlazasActual(convocatoria));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (ConvocatoriaNotFoundException e){
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria a la que desea acceder no existe.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }

        mav.setViewName("private/convocatoria/convocatoria");
        return mav;
    }

    @GetMapping("/opositores")
    public ModelAndView listadoOpositores(String idconvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idconvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("opositoresValidados",convocatoriaService.getOpositoresConvocatoria(idconvo, true));
            mav.addObject("opositoresNoValidados",convocatoriaService.getOpositoresConvocatoria(idconvo, false));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener los opositores de la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("private/convocatoria/opositores");
        return mav;
    }

    @PostMapping("/validarOpositor")
    public ModelAndView validarOpositor(@RequestParam String idUsuario,@RequestParam String idConvo, @RequestParam Integer orden, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), idConvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permisos para realizar esta acción.", Constantes.ALERTA_TIPO_ERROR));
            }else{
                if (orden.intValue()<=0){
                    ra.addFlashAttribute("alerta", new Alerta("Alerta", "El orden no puede ser negativo.", Constantes.ALERTA_TIPO_ERROR));
                }else{
                    //TODO comprobar que ocurre si llega vacio
                    convocatoriaService.validarOpositor(idUsuario, idConvo, orden);
                }
            }
        }catch (OpositorNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "No se ha encontrado el opositor.", Constantes.ALERTA_TIPO_ERROR));
        }catch (OrdenOpositorIncorrectoException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ya existe un opositor con ese orden.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error inesperado.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("redirect:/convocatoria/opositores");
        return mav;
    }


    @GetMapping("/rechazarOpositor")
    public ModelAndView rechazarOpositor(String idUsuario, String idConvo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), idConvo)) {
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permisos para realizar esta acción.", Constantes.ALERTA_TIPO_ERROR));
            } else {
                convocatoriaService.rechazarOpositor(idUsuario, idConvo);
            }
        } catch (OpositorNotFoundException e) {
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "No se ha encontrado el opositor.", Constantes.ALERTA_TIPO_ERROR));
        } catch (Exception e) {
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error inesperado.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("redirect:/convocatoria/opositores");
        return mav;
    }


    @GetMapping("/plazas")
    public ModelAndView listadoPlazas(String idconvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idconvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("plazas",convocatoriaService.getPlazasConvocatoria(idconvo));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener las plazas de la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("private/convocatoria/plazas");
        return mav;
    }

}
