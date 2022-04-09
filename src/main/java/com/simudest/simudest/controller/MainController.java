package com.simudest.simudest.controller;

import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.service.AuthenticationService;
import com.simudest.simudest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class MainController {

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
}
