package com.simudest.simudest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdministrationController {

    @GetMapping("/organismos")
    public ModelAndView listadoOrganismos() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("");
        return mav;
    }
}
