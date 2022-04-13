package com.simudest.simudest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            mav.addObject("convocatoria", convocatoriaService.getConvocatoria(id));
        }catch (ConvocatoriaNotFoundException e){
        	ra.addFlashAttribute("alerta", new Alerta("Alerta", "La convocatoria a la que desea acceder no existe.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_PRINCIPAL);
            return mav;
        }

        mav.setViewName("private/convocatoria/convocatoria");
        return mav;
    }
}
