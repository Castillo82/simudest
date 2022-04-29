package com.simudest.simudest.controller;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.*;
import com.simudest.simudest.exception.*;
import com.simudest.simudest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class MainController {


    @Autowired
    private MainService mainService;

    @GetMapping("")
    public ModelAndView principal(Alerta alerta) {
        ModelAndView mav = new ModelAndView();
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ConvocatoriaDto> misConvocatorias = mainService.getMisConvocatorias(user.getUsername());
        List <ConvocatoriaDto> otrasConvocatorias = mainService.getConvocatoriasActivas();
        otrasConvocatorias.removeAll(misConvocatorias);
        mav.addObject("misConvocatorias", misConvocatorias);
        mav.addObject("otrasConvocatorias", otrasConvocatorias);
        mav.setViewName("private/principal");
        return mav;
    }

    @GetMapping("/nuevaConvocatoria")
    public ModelAndView nuevaConvocatoria() {
        ModelAndView mav = new ModelAndView();
        ConvocatoriaDto convocatoriaDto = new ConvocatoriaDto();
        mav.addObject("convocatoria", convocatoriaDto);
        mav.addObject("organismos", mainService.getOrganismos());
        mav.addObject("grupos", mainService.getGrupos());
        mav.addObject("especialidades", mainService.getEspecialidades());
        mav.setViewName("private/convocatoria/modificarConvocatoria");
        return mav;
    }

    @GetMapping("/modificarConvocatoria")
    public ModelAndView modificarConvocatoria(@RequestParam String idConvo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ConvocatoriaDto convocatoriaDto = mainService.getConvocatoria(idConvo);
            mav.addObject("convocatoria", convocatoriaDto);
            mav.addObject("organismos", mainService.getOrganismos());
            mav.addObject("grupos", mainService.getGrupos());
            mav.addObject("especialidades", mainService.getEspecialidades());
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error.", Constantes.ALERTA_TIPO_ERROR));
        }

        mav.setViewName("private/convocatoria/modificarConvocatoria");
        return mav;
    }

    @PostMapping("/guardarConvocatoria")
    public ModelAndView guardarConvocatoria(ConvocatoriaDto convocatoriaDto, final BindingResult bindingResult){
        //TODO se debe comprobar si el usuario tiene permiso para editar la convocatoria
        //TODO considerar refactor de este metodo, demasiada logica en el controlador
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }
        try {
            if (convocatoriaDto.getId() == null) {
                convocatoriaDto.setEstado(Constantes.CONVOCATORIA_ESTADO_ACTIVA);
            }
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            convocatoriaDto.setUsuarioDto(mainService.getUsuariobyId(user.getUsername()));

            EspecialidadDto especialidadDto = mainService.getEspecialidadbyId(convocatoriaDto.getEspecialidadDto().getId());
            OrganismoDto organismoDto = mainService.getOrganismobyId(convocatoriaDto.getOrganismoDto().getId());
            convocatoriaDto.setEspecialidadDto(especialidadDto);
            convocatoriaDto.setOrganismoDto(organismoDto);
            mainService.guardarConvocatoria(convocatoriaDto);
        }catch (EspecialidadNotFoundException e){
            bindingResult.rejectValue("especialidad", "especialidad","La especialidad seleccionada no existe.");
        }catch (OrganismoNotFoundException e){
            bindingResult.rejectValue("organismo", "organismo","El organismo seleccionado no existe.");
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }

        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }

    @GetMapping("/eliminarConvocatoria")
    public ModelAndView eliminarConvocatoria(@RequestParam String idConvo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            mainService.eliminarConvocatoria(idConvo, user.getUsername());
            ra.addFlashAttribute("alerta", new Alerta("Información", "Ha eliminado la convocatoria.", Constantes.ALERTA_TIPO_INFO));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (SinPermisoException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "No tiene permiso para eliminar esta convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al eliminar la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }

    @PostMapping("/solicitarAcceso")
    public ModelAndView solicitarAcceso(@RequestParam String idConvo, @RequestParam String palabra, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
        	User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	mainService.solicitarAcceso(idConvo, user.getUsername(), palabra);
        	ra.addFlashAttribute("alerta", new Alerta("Información", "Ha solicitado correctamente el acceso a la convocatoria. Tan pronto como el organizador de la convocatoria acepte su solicitud, podrá acceder a ella.", Constantes.ALERTA_TIPO_INFO));
        }catch (ConvocatoriaNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria solicitada no existe o es incorrecta.", Constantes.ALERTA_TIPO_ERROR));
        }catch (UsuarioNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error recuperando su usuario.", Constantes.ALERTA_TIPO_ERROR));
        }catch (OpositorAlreadyExistException e){
            ra.addFlashAttribute("alerta", new Alerta("Atención", "Ya ha solicitado acceso a esa convocatoria, debe esperar a que el organizador de la convocatoria le otorgue acceso para poder verla en 'Mis convocatorias'.", Constantes.ALERTA_TIPO_WARNING));
        }catch (PalabraIncorrectaException e){
            ra.addFlashAttribute("alerta", new Alerta("Atención", "La palabra que ha introducido no es correcta. Contacte con el organizador de la convocatoria para que le indique la palabra secreta de acceso.", Constantes.ALERTA_TIPO_WARNING));
        }catch (Exception e){
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al solicitar el acceso a la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }   	
        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }

    // Ajax para el select de grupos y especialidades
    @RequestMapping(value = "/ajax/especialidades")
    @ResponseBody
    public List<EspecialidadDto> getEspecialidades(@RequestParam Integer grupo) {
        return mainService.getEspecialidadesbyGrupo(grupo);
    }

}
