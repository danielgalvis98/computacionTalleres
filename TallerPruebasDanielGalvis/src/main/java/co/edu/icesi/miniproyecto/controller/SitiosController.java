package co.edu.icesi.miniproyecto.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.delegate.SitiosDelegate;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

@RequestMapping("/sitio")
@Controller
public class SitiosController {
	SitiosDelegate sitiosDelegate;
	
	@Autowired
	public SitiosController(SitiosDelegate sitiosDelegate) {
		this.sitiosDelegate=sitiosDelegate;
	}
	
	@GetMapping("")
	public String indexSitios(Model model) {
		model.addAttribute("sitios",sitiosDelegate.getAllSitios());
		return "sitios/index";
	}
	
	@GetMapping("/add")
	public String addSitio(Model model) {
		model.addAttribute("tmio1sitio",new Tmio1Sitio());
		return "sitios/add";
	}
	
	@PostMapping("/add")
	public String saveSitio(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Sitio tmio1Sitio, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) 
				return "sitios/add";
			else
				sitiosDelegate.addTmio1Sitio(tmio1Sitio);
		}
		
		return "redirect:/sitio";
	}
	
	@GetMapping("/edit/{idSitio}")
	public String editSitio(@PathVariable("idSitio") long idSitio, Model model) {
		model.addAttribute("tmio1Sitio",sitiosDelegate.getSitioById(idSitio));
		return "sitios/edit";
	}
	
	@PostMapping("/edit/{idSitio}")
	public String editPostSitio(@PathVariable("idSitio") long idSitio,
			@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Sitio tmio1Sitio, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "sitios/edit";
			} else {
				tmio1Sitio.setId(idSitio);
				sitiosDelegate.updateTmio1Sitio(tmio1Sitio);
			}
		}
		
		return "redirect:/sitio";
	}
	
	@GetMapping("/remove/{idSitio}")
	public String removeSitio(@PathVariable("idSitio") long idSitio, Model model) {
		sitiosDelegate.removeSitio(sitiosDelegate.getSitioById(idSitio));
		return "redirect:/sitio";
	}
}
