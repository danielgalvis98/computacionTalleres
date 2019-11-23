package co.edu.icesi.miniproyecto.restControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.services.RutasServicio;

@RequestMapping("/api/ruta")
@RestController
public class ApiRutaController {
	RutasServicio rutaService;
	
	@Autowired
	public ApiRutaController(RutasServicio service) {
		rutaService = service;
	}
	
	@GetMapping("")
	public Iterable<Tmio1Ruta> getRutas(){
		return rutaService.getAllRutas();
	}
	
	@PostMapping("/add")
	public void addTmio1Ruta(@RequestBody Tmio1Ruta ruta) throws Exception {
		System.out.println(ruta.getDiaFin());
		System.out.println(ruta.getDiaInicio());
		rutaService.addRuta(ruta);
	}
	
	@GetMapping("/{id}")
	public Tmio1Ruta getRuta(@PathVariable Integer id) {
		return rutaService.getRuta(id);
	}

}
