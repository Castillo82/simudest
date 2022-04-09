package com.simudest.simudest.controller;

import com.simudest.simudest.dto.UsuarioDto;
import com.simudest.simudest.exception.UserAlreadyExistException;
import com.simudest.simudest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("")
public class AuthenticationController {

	private static final String REDIRECT_LOGIN= "redirect:/login";

	@Autowired
	private AuthenticationService authenticationService;

/*
	@GetMapping("")
	public ModelAndView portada(String mensaje) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("public/portada");
		return mav;
	}
*/
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("public/login");
		return mav;
	}


	@GetMapping("/registro")
	public ModelAndView registro() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("usuarioDto", new UsuarioDto());
		mav.setViewName("public/registro");
		return mav;
	}

	@PostMapping("/registro")
	public ModelAndView userRegistration(UsuarioDto usuarioDto, final BindingResult bindingResult){
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()){
			mav.addObject("usuario", usuarioDto);
			mav.setViewName("public/registro");
			return mav;
		}
		try {
			authenticationService.registrar(usuarioDto);
		}catch (UserAlreadyExistException e){
			bindingResult.rejectValue("email", "usuario.email","Ya existe una cuenta con este email.");
			mav.addObject("registrationForm", usuarioDto);
			mav.setViewName("public/registro");
			return mav;
		}

		mav.setViewName(REDIRECT_LOGIN);
		return mav;
	}


	@GetMapping("/quees")
	public ModelAndView quees() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("public/quees");
		return mav;
	}

}