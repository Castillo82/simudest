package com.simudest.simudest.controller;

import com.simudest.simudest.configuration.Constantes;
import com.simudest.simudest.dto.*;
import com.simudest.simudest.exception.*;
import com.simudest.simudest.service.AdministrationService;
import com.simudest.simudest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdministrationController {
    @Autowired
    private AdministrationService administrationService;

    @GetMapping("/organismos")
    public ModelAndView listadoOrganismos(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("organismos",administrationService.getAllOrganismos());
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener los organismos.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("admin/organismos");
        return mav;
    }

    @GetMapping("/organismos/nuevo")
    public ModelAndView modificarOrganismo(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        OrganismoDto organismoDto = new OrganismoDto();
        mav.addObject("organismo", organismoDto);
        mav.setViewName("admin/modificarOrganismo");
        return mav;
    }

    @GetMapping("/organismos/modificar")
    public ModelAndView modificarOrganismo(Integer idOrga, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        OrganismoDto organismoDto;
        try {
            organismoDto = administrationService.getOrganismoById(idOrga);
        }catch (OrganismoNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar modificar el organismo.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_ADMIN_ORGANISMOS);
            return mav;
        }
        mav.addObject("organismo", organismoDto);
        mav.setViewName("admin/modificarOrganismo");
        return mav;
    }


    @PostMapping("/organismos/guardar")
    public ModelAndView guardarOrganismo(OrganismoDto organismoDto, final BindingResult bindingResult, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        boolean nuevoOrganismo = organismoDto.getId() == null;
        if(bindingResult.hasErrors()){
            mav.addObject("organismo", organismoDto);
            mav.setViewName("admin/modificarOrganismo");
            return mav;
        }
        try {
            administrationService.guardarOrganismo(organismoDto);
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("organismo", organismoDto);
            mav.setViewName("admin/modificarOrganismo");
            return mav;
        }
        if (nuevoOrganismo){
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha creado el organismo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }else{
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha modificado el organismo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_ORGANISMOS);
        return mav;
    }

    @GetMapping("/organismos/eliminar")
    public ModelAndView eliminarOrganismo(Integer idOrga, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        OrganismoDto organismoDto;
        try {
            organismoDto = administrationService.getOrganismoById(idOrga);
            administrationService.eliminarOrganismo(organismoDto);
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha eliminado el organismo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }catch (OrganismoNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar eliminar el organismo.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_ORGANISMOS);
        return mav;
    }


    @GetMapping("/grupos")
    public ModelAndView listadoGrupos(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("grupos",administrationService.getAllGrupos());
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener los grupos.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("admin/grupos");
        return mav;
    }

    @GetMapping("/grupos/nuevo")
    public ModelAndView modificarGrupo(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        GrupoDto grupoDto = new GrupoDto();
        mav.addObject("grupo", grupoDto);
        mav.setViewName("admin/modificarGrupo");
        return mav;
    }

    @GetMapping("/grupos/modificar")
    public ModelAndView modificarGrupo(Integer idGrupo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        GrupoDto grupoDto;
        try {
            grupoDto = administrationService.getGrupoById(idGrupo);
        }catch (GrupoNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar modificar el grupo.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_ADMIN_GRUPOS);
            return mav;
        }
        mav.addObject("grupo", grupoDto);
        mav.setViewName("admin/modificarGrupo");
        return mav;
    }


    @PostMapping("/grupos/guardar")
    public ModelAndView guardarGrupo(GrupoDto grupoDto, final BindingResult bindingResult, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        boolean nuevoGrupo = grupoDto.getId() == null;
        if(bindingResult.hasErrors()){
            mav.addObject("grupo", grupoDto);
            mav.setViewName("admin/modificarGrupo");
            return mav;
        }
        try {
            administrationService.guardarGrupo(grupoDto);
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("grupo", grupoDto);
            mav.setViewName("admin/modificarGrupo");
            return mav;
        }
        if (nuevoGrupo){
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha creado el grupo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }else{
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha modificado el grupo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_GRUPOS);
        return mav;
    }

    @GetMapping("/grupos/eliminar")
    public ModelAndView eliminarGrupo(Integer idGrupo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        GrupoDto grupoDto;
        try {
            grupoDto = administrationService.getGrupoById(idGrupo);
            administrationService.eliminarGrupo(grupoDto);
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha eliminado el grupo correctamente.", Constantes.ALERTA_TIPO_INFO));
        }catch (GrupoNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar eliminar el grupo.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_GRUPOS);
        return mav;
    }

    @GetMapping("/especialidades")
    public ModelAndView listadoEspecialidades(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("especialidades",administrationService.getAllEspecialidades());
        }catch (Exception e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al obtener los especialidades.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName("admin/especialidades");
        return mav;
    }

    @GetMapping("/especialidades/nuevo")
    public ModelAndView modificarEspecialidad(RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        EspecialidadDto especialidadDto = new EspecialidadDto();
        mav.addObject("especialidad", especialidadDto);
        mav.addObject("grupos", administrationService.getAllGrupos());
        mav.setViewName("admin/modificarEspecialidad");
        return mav;
    }

    @GetMapping("/especialidades/modificar")
    public ModelAndView modificarEspecialidad(Integer idEspecialidad, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        EspecialidadDto especialidadDto;
        try {
            especialidadDto = administrationService.getEspecialidadById(idEspecialidad);
        }catch (EspecialidadNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar modificar el especialidad.", Constantes.ALERTA_TIPO_ERROR));
            mav.setViewName(Constantes.REDIRECT_ADMIN_ESPECIALIDADES);
            return mav;
        }
        mav.addObject("especialidad", especialidadDto);
        mav.addObject("grupos", administrationService.getAllGrupos());
        mav.setViewName("admin/modificarEspecialidad");
        return mav;
    }


    @PostMapping("/especialidades/guardar")
    public ModelAndView guardarEspecialidad(EspecialidadDto especialidadDto, final BindingResult bindingResult, RedirectAttributes ra){
        ModelAndView mav = new ModelAndView();
        boolean nuevoEspecialidad = especialidadDto.getId() == null;
        if(bindingResult.hasErrors()){
            mav.addObject("especialidad", especialidadDto);
            mav.setViewName("admin/modificarEspecialidad");
            return mav;
        }
        try {
            GrupoDto grupoDto = administrationService.getGrupoById(especialidadDto.getGrupoDto().getId());
            especialidadDto.setGrupoDto(grupoDto);
            administrationService.guardarEspecialidad(especialidadDto);
        }catch (Exception e){
            bindingResult.rejectValue("nombre", "nombre","Ha ocurrido un error inesperado.");
            mav.addObject("especialidad", especialidadDto);
            mav.setViewName("admin/modificarEspecialidad");
            return mav;
        }
        if (nuevoEspecialidad){
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha creado el especialidad correctamente.", Constantes.ALERTA_TIPO_INFO));
        }else{
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha modificado el especialidad correctamente.", Constantes.ALERTA_TIPO_INFO));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_ESPECIALIDADES);
        return mav;
    }

    @GetMapping("/especialidades/eliminar")
    public ModelAndView eliminarEspecialidad(Integer idEspecialidad, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        EspecialidadDto especialidadDto;
        try {
            especialidadDto = administrationService.getEspecialidadById(idEspecialidad);
            administrationService.eliminarEspecialidad(especialidadDto);
            ra.addFlashAttribute("alerta", new Alerta("Información", "Se ha eliminado el especialidad correctamente.", Constantes.ALERTA_TIPO_INFO));
        }catch (EspecialidadNotFoundException e){
            ra.addFlashAttribute("alerta", new Alerta("Alerta", "Ha ocurrido un error al intentar eliminar el especialidad.", Constantes.ALERTA_TIPO_ERROR));
        }
        mav.setViewName(Constantes.REDIRECT_ADMIN_ESPECIALIDADES);
        return mav;
    }

}
