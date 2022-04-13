package com.simudest.simudest.controller;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.Alerta;
import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.entity.Usuario;
import com.simudest.simudest.exception.UserAlreadyExistException;
import com.simudest.simudest.service.AuthenticationService;
import com.simudest.simudest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("")
public class MainController {


    @Autowired
    private MainService mainService;

    @GetMapping("")
    public ModelAndView principal(Alerta alerta) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("misConvocatorias", mainService.getConvocatoriasActivas());
        mav.addObject("convocatoriasActivas", mainService.getConvocatoriasActivas());
        mav.setViewName("private/principal");
        return mav;
    }

    @GetMapping("/nuevaConvocatoria")
    public ModelAndView nuevaConvocatoria() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("convocatoria", new ConvocatoriaDto());
        mav.addObject("organismos", mainService.getOrganismos());
        mav.addObject("grupos", mainService.getGrupos());
        mav.addObject("especialidades", mainService.getEspecialidades());
        mav.setViewName("private/convocatoria/modificarConvocatoria");
        return mav;
    }

    @PostMapping("/guardarConvocatoria")
    public ModelAndView guardarConvocatoria(ConvocatoriaDto convocatoriaDto, final BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }
        try {
            mainService.guardarConvocatoria(convocatoriaDto);
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }

        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }

    @GetMapping("/solicitarAcceso")
    public ModelAndView solicitarAcceso(String idconvo, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
        	User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	mainService.solicitarAcceso(idconvo, user.getUsername());
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha solicitado correctamente el acceso a la convocatoria. Tan pronto como el organizador de la convocatoria acepte su solicitud, podrá acceder a ella.", Constantes.ALERTA_TIPO_INFO));
        }catch (Exception e){
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al solicitar el acceso a la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }   	
        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }
   
    /*
    @PostMapping("/solicitarAcceso")
    public ModelAndView solicitarAcceso(ConvocatoriaDto convocatoriaDto, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        try {
        	User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            //mainService.solicitarAcceso(convocatoria, user.getUsername());
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha solicitado correctamente el acceso a la convocatoria. Tan pronto como el organizador de la convocatoria acepte su solicitud, podrá acceder a ella.", Constantes.ALERTA_TIPO_INFO));
        }catch (Exception e){
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al solicitar el acceso a la convocatoria.", Constantes.ALERTA_TIPO_ERROR));
        }   	
        mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
        return mav;
    }
*/
    
    // Ajax para el select de grupos y especialidades
    @RequestMapping(value = "/ajax/especialidades")
    @ResponseBody
    public List<EspecialidadDto> getEspecialidades(@RequestParam Integer grupo) {
        return mainService.getEspecialidadesbyGrupo(grupo);
    }

}
