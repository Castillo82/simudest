package com.simudest.simudest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/convocatoria")
public class ConvocatoriaController {

    @GetMapping("")
    public ModelAndView convocatoria() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("");
        return mav;
    }
}
