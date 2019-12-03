package co.edu.icesi.miniproyecto.controller;

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

import co.edu.icesi.miniproyecto.delegate.RutaDelegate;
import co.edu.icesi.miniproyecto.delegate.SitiosDelegate;
import co.edu.icesi.miniproyecto.delegate.SitiosRutaDelegate;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

@RequestMapping("/sitioruta")
@Controller
public class SitiosRutaController {
	
	private SitiosRutaDelegate sitioRutaDelegate;
	private SitiosDelegate sitiosDelegate;
	private RutaDelegate rutaDelegate;
	
	@Autowired
	public SitiosRutaController(SitiosRutaDelegate sitrutD, SitiosDelegate sitD,
			RutaDelegate rutD) {
		sitioRutaDelegate = sitrutD;
		sitiosDelegate = sitD;
		rutaDelegate = rutD;
	}
	
	@GetMapping("")
	public String indexSitioRuta(Model model) {
		model.addAttribute("tmioSitiosRutaPK", new Tmio1SitiosRutaPK());
		
		model.addAttribute("sitiosrutas", sitioRutaDelegate.getAllSitiosRuta());
		return "sitiosruta/index";
	}
	
	@GetMapping("/add")
	public String addSitioRuta (Model model) {
		model.addAttribute("tmioSitiosRutaPK", new Tmio1SitiosRutaPK());
		
		model.addAttribute("sitios", sitiosDelegate.getAllSitios());
		model.addAttribute("rutas", rutaDelegate.getAllRutas());
		
		return "sitiosruta/add";
	}
	
	@PostMapping("/add")
	public String saveServicioRuta (@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1SitiosRutaPK sitioRutaPk, BindingResult bindingResult, Model model) {
		
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("sitios", sitiosDelegate.getAllSitios());
				model.addAttribute("rutas", rutaDelegate.getAllRutas());
				
				return "sitiosruta/add";
			}
			else {
				Tmio1SitiosRuta sitioRuta = new Tmio1SitiosRuta();
				sitioRuta.setId(sitioRutaPk);
				try {
					sitioRutaDelegate.addSitioRuta(sitioRuta);
				} catch (RuntimeException e) {
					model.addAttribute("sitios", sitiosDelegate.getAllSitios());
					model.addAttribute("rutas", rutaDelegate.getAllRutas());
					model.addAttribute("exception", e.getMessage());
					
					return "sitiosruta/add";
				}
			}
		}
		
		
		return "redirect:/sitioruta";
	}
	
	@GetMapping("/edit/{idRuta}/{idSitio}")
	public String showUpdateSitioRuta (@PathVariable("idRuta") int idRuta, @PathVariable("idSitio") int idSitio,
			Model model) {
		Tmio1SitiosRutaPK key = new Tmio1SitiosRutaPK();
		key.setIdRuta(idRuta);
		key.setIdSitio(idSitio);
		
		Tmio1SitiosRuta sitioRuta = sitioRutaDelegate.getSitioRuta(key);
		if (sitioRuta == null)
			throw new IllegalArgumentException("Combinación de parámetros inválida");
		
		model.addAttribute("tmioSitiosRutaPK", sitioRuta.getId());
		model.addAttribute("sitios", sitiosDelegate.getAllSitios());
		model.addAttribute("rutas", rutaDelegate.getAllRutas());
		
		return "sitiosruta/edit";
	}
	
	@PostMapping("/edit/{idRuta}/{idSitio}")
	public String updateSitioRuta (@Valid Tmio1SitiosRutaPK sitioRutaPk, @PathVariable("idRuta") int idRuta, @PathVariable("idSitio") int idSitio,
			Model model, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("sitios", sitiosDelegate.getAllSitios());
				model.addAttribute("rutas", rutaDelegate.getAllRutas());
				
				return "sitiosruta/edit";
			}
			else {
				Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
				pk.setIdRuta(idRuta);
				pk.setIdSitio(idSitio);
				
				Tmio1SitiosRuta sitioRuta = new Tmio1SitiosRuta();
				sitioRuta.setId(sitioRutaPk);
				
				try{
					sitioRutaDelegate.addSitioRuta(sitioRuta);
					Tmio1SitiosRuta oldSitioRuta = sitioRutaDelegate.getSitioRuta(pk);
					if (!sitioRutaPk.equals(pk))
						sitioRutaDelegate.removeSitioRuta(oldSitioRuta);
				} catch (RuntimeException e) {
					model.addAttribute("sitios", sitiosDelegate.getAllSitios());
					model.addAttribute("rutas", rutaDelegate.getAllRutas());
					model.addAttribute("exception", e.getMessage());
					
					return "sitiosruta/edit";
				}
			}
		}
		return "redirect:/sitioruta";
	}
	
	@GetMapping("/remove/{idRuta}/{idSitio}")
	public String removeSitio(@PathVariable("idRuta") int idRuta, 
			@PathVariable("idSitio") int idSitio, Model model) {
		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		pk.setIdRuta(idRuta);
		pk.setIdSitio(idSitio);
		
		sitioRutaDelegate.removeSitioRuta(sitioRutaDelegate.getSitioRuta(pk));
		return "redirect:/sitioruta";
	}

}
