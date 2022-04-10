package com.simudest.simudest.controller;

import com.simudest.simudest.dto.ConvocatoriaDto;
import com.simudest.simudest.dto.EspecialidadDto;
import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.exception.UserAlreadyExistException;
import com.simudest.simudest.service.AuthenticationService;
import com.simudest.simudest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("")
public class MainController {

    private static final String REDIRECT_PRINCIPAL= "redirect:/";

    @Autowired
    private MainService mainService;

    @GetMapping("")
    public ModelAndView principal() {
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

    @PostMapping("/nuevaConvocatoria")
    public ModelAndView nuevaConvocatoria(ConvocatoriaDto convocatoriaDto, final BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }
        try {
            //mainService.guardarConvocatoria(convocatoriaDto);
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("convocatoria", convocatoriaDto);
            mav.setViewName("private/convocatoria/modificarConvocatoria");
            return mav;
        }

        mav.setViewName(REDIRECT_PRINCIPAL);
        return mav;
    }



    // Ajax para el select de grupos y especialidades
    @RequestMapping(value = "/ajax/especialidades")
    @ResponseBody
    public List<EspecialidadDto> getEspecialidades(@RequestParam Integer grupo) {
        return mainService.getEspecialidadesbyGrupo(grupo);
    }

}
