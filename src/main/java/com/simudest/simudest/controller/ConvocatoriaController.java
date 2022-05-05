package com.simudest.simudest.controller;

import com.simudest.simudest.dto.*;
import com.simudest.simudest.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.service.ConvocatoriaService;

import java.util.List;
import java.util.Map;

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
    public ModelAndView listadoOpositores(String idConvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idConvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("opositoresValidados",convocatoriaService.getOpositoresConvocatoria(idConvo, true));
            mav.addObject("opositoresNoValidados",convocatoriaService.getOpositoresConvocatoria(idConvo, false));
            mav.addObject("convocatoria",convocatoriaService.getConvocatoria(idConvo));
            mav.addObject("puedeAdministrarConvocatoria",convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), idConvo));
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
        ra.addAttribute("idConvo", idConvo);
        mav.setViewName(Constantes.REDIRECT_OPOSITORES);
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
        ra.addAttribute("idConvo", idConvo);
        mav.setViewName(Constantes.REDIRECT_OPOSITORES);
        return mav;
    }


    @GetMapping("/plazas")
    public ModelAndView listadoPlazas(String idConvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idConvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("plazas",convocatoriaService.getPlazasConvocatoria(idConvo));
            mav.addObject("convocatoria",convocatoriaService.getConvocatoria(idConvo));
            mav.addObject("puedeAdministrarConvocatoria",convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), idConvo));
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

    @GetMapping("/nuevaPlaza")
    public ModelAndView nuevaPlaza(String idConvo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        PlazaDto plazaDto = new PlazaDto();
        try {
            ConvocatoriaDto convocatoriaDto = convocatoriaService.getConvocatoria(idConvo);
            plazaDto.setConvocatoriaDto(convocatoriaDto);
            mav.addObject("plaza", plazaDto);
            mav.addObject("provincias", convocatoriaService.getProvincias());
        }catch (ConvocatoriaNotFoundException e) {
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }
        mav.setViewName("private/convocatoria/modificarPlaza");
        return mav;
    }

    @GetMapping("/modificarPlaza")
    public ModelAndView modificarPlaza(String idPlaza, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        PlazaDto plazaDto;
        try {
            plazaDto = convocatoriaService.getPlazabyId(idPlaza);
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), plazaDto.getConvocatoriaDto().getId())){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permisos para realizar esta acción.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }catch (PlazaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar modificar la plaza.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }
        mav.addObject("plaza", plazaDto);
        mav.addObject("provincias", convocatoriaService.getProvincias());
        mav.setViewName("private/convocatoria/modificarPlaza");
        return mav;
    }

    @PostMapping("/guardarPlaza")
    public ModelAndView guardarPlaza(PlazaDto plazaDto, final BindingResult bindingResult, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        boolean nuevaPlaza = plazaDto.getId() == null;
        if(bindingResult.hasErrors()){
            mav.addObject("plaza", plazaDto);
            mav.setViewName("private/convocatoria/modificarPlaza");
            return mav;
        }
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), plazaDto.getConvocatoriaDto().getId())){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permisos para realizar esta acción.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }

            ConvocatoriaDto convocatoriaDto = convocatoriaService.getConvocatoria(plazaDto.getConvocatoriaDto().getId());
            ProvinciaDto provinciaDto = convocatoriaService.getProvincia(plazaDto.getProvinciaDto().getId());
            plazaDto.setConvocatoriaDto(convocatoriaDto);
            plazaDto.setProvinciaDto(provinciaDto);
            convocatoriaService.guardarPlaza(plazaDto);
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            bindingResult.rejectValue("codigo", "codigo","Ha ocurrido un error inesperado.");
            mav.addObject("plaza", plazaDto);
            mav.setViewName("private/convocatoria/modificarPlaza");
            return mav;
        }

        ra.addAttribute("idConvo", plazaDto.getConvocatoriaDto().getId());
        if (nuevaPlaza){
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha creado la plaza correctamente.", Constantes.ALERTA_TIPO_INFO));
        }else{
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha modificado la plaza correctamente.", Constantes.ALERTA_TIPO_INFO));
        }
        mav.setViewName(Constantes.REDIRECT_PLAZAS);
        return mav;
    }

    @GetMapping("/borrarPlaza")
    public ModelAndView borrarPlaza(String idPlaza, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        PlazaDto plazaDto;
        try {
            plazaDto = convocatoriaService.getPlazabyId(idPlaza);
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeAdministrarConvocatoria(user.getUsername(), plazaDto.getConvocatoriaDto().getId())){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permisos para realizar esta acción.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            convocatoriaService.borrarPlaza(plazaDto);
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }catch (PlazaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar eliminar la plaza.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }
        ra.addAttribute("idConvo", plazaDto.getConvocatoriaDto().getId());
        ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha borrado la plaza correctamente.", Constantes.ALERTA_TIPO_INFO));
        mav.setViewName(Constantes.REDIRECT_PLAZAS);
        return mav;
    }


    @GetMapping("/seleccionarPLazas")
    public ModelAndView seleccionarPLazas(String idConvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idConvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("elecciones",convocatoriaService.getElecciones(user.getUsername(), idConvo));
            mav.addObject("plazas",convocatoriaService.getPlazasConvocatoria(idConvo));
            mav.addObject("convocatoria",convocatoriaService.getConvocatoria(idConvo));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener las plazas de la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("private/convocatoria/seleccionarPlazas");
        return mav;
    }



    // Ajax para seleccionar plaza
    @RequestMapping(value = "/ajax/seleccionarPlaza")
    @ResponseBody
    public String seleccionarPlaza(@RequestParam String idPlaza, @RequestParam Integer orden) {
        try {
            PlazaDto plazaDto = convocatoriaService.getPlazabyId(idPlaza);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), plazaDto.getConvocatoriaDto().getId())) {
                return "No tiene permisos para seleccionar plazas en esta convocatoria.";
            }
            convocatoriaService.seleccionarPlaza(plazaDto, user.getUsername(), orden);
        }catch (PlazaYaElegidaException e){
            return "Ya ha elegido esa plaza en otro orden, bórrela primero antes de volverla a elegir.";
        }catch (Exception e){
            return "Ha ocurrido un error inesperado al seleccionar la plaza.";
        }
        return "OK";

    }

    // Ajax para eliminar la seleccion de plaza
    @RequestMapping(value = "/ajax/eliminarSeleccion")
    @ResponseBody
    public String eliminarSeleccionPlaza(@RequestParam String idConvo, @RequestParam Integer orden) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idConvo)) {
                return "No tiene permisos para seleccionar plazas en esta convocatoria.";
            }
            convocatoriaService.eliminarSeleccionPlaza(idConvo, user.getUsername(), orden);
        }catch (EleccionNotFoundException e){
            return "No hay ninguna plaza elegida en este orden, no se ha borrado ninguna selección.";
        }catch (Exception e){
            return "Ha ocurrido un error inesperado al eliminar la selección de plaza.";
        }
        return "OK";

    }

    @GetMapping("/consultarResultadoSimulacion")
    public ModelAndView consultarResultadoSimulacion(String idConvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!convocatoriaService.puedeConsultarConvocatoria(user.getUsername(), idConvo)){
                ra.addFlashAttribute("alerta", new Alerta("Alerta", "El usuario no tiene permiso para acceder a esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
                mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
                return mav;
            }
            mav.addObject("convocatoria",convocatoriaService.getConvocatoria(idConvo));
            mav.addObject("elecciones",convocatoriaService.getResultadoSimulacion(user.getUsername(), idConvo));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al calcular el resultado de la simulación.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("private/convocatoria/resultadoSimulacion");
        return mav;
    }

}
