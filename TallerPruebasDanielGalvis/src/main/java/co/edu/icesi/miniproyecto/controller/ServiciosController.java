package co.edu.icesi.miniproyecto.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.services.BusesServicio;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;
import co.edu.icesi.miniproyecto.services.RutasServicio;
import co.edu.icesi.miniproyecto.services.ServiciosServicio;
import co.edu.icesi.miniproyecto.validation.ValidateDate;

@RequestMapping("/servicio")
@Controller
public class ServiciosController {
	
	ServiciosServicio serviciosServicio;
	BusesServicio busesServicio;
	ConductoresServicio conductoresServicio;
	RutasServicio rutasServicio;
	
	@Autowired
	public ServiciosController(ServiciosServicio serviciosServicio, BusesServicio busesServicio,
			ConductoresServicio conductoresServicio, RutasServicio rutasServicio) {
		this.serviciosServicio = serviciosServicio;
		this.busesServicio = busesServicio;
		this.conductoresServicio = conductoresServicio;
		this.rutasServicio = rutasServicio;
	}
	
	@GetMapping("")
	public String indexServicio(Model model) {
		model.addAttribute("tmio1ServicioPK", new Tmio1ServicioPK());
		
		model.addAttribute("servicios", serviciosServicio.getAllServicios());
		return "servicios/index";
	}
	
	@GetMapping("/add")
	public String addServicio(Model model) {
		model.addAttribute("tmio1ServicioPK", new Tmio1ServicioPK());
		
		model.addAttribute("buses", busesServicio.getAllBuses());
		model.addAttribute("conductores", conductoresServicio.getAllConductores());
		model.addAttribute("rutas", rutasServicio.getAllRutas());
		
		return "servicios/add";
	}
	
	@PostMapping("/add")
	public String saveServicio(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1ServicioPK servicePk, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("buses", busesServicio.getAllBuses());
				model.addAttribute("conductores", conductoresServicio.getAllConductores());
				model.addAttribute("rutas", rutasServicio.getAllRutas());
				
				return "servicios/add";
			}
			else {
				Tmio1Servicio service = new Tmio1Servicio();
				service.setId(servicePk);
				service.setTmio1Bus(busesServicio.getBus(servicePk.getIdBus()));
				service.setTmio1Conductore(conductoresServicio.getConductor(servicePk.getCedulaConductor()));
				service.setTmio1Ruta(rutasServicio.getRuta(servicePk.getIdRuta()));
				try {
					serviciosServicio.addServicio(service);
				} catch (Exception e) {
					model.addAttribute("buses", busesServicio.getAllBuses());
					model.addAttribute("conductores", conductoresServicio.getAllConductores());
					model.addAttribute("rutas", rutasServicio.getAllRutas());
					model.addAttribute("exception", e.getMessage());
					return "servicios/add";
				}
			}
		}
		return "redirect:/servicio";
	}
	
	@GetMapping("/edit/{idRuta}/{cedulaConductor}/{idBus}/{fechaInicio}/{fechaFin}")
	public String showUpdateService(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin, Model model) {
		LocalDate inicio = LocalDate.parse(fechaInicio);
		LocalDate fin = LocalDate.parse(fechaFin);
		
		Tmio1ServicioPK key = new Tmio1ServicioPK();
		
		key.setCedulaConductor(cedulaConductor);
		key.setFechaFin(fin);
		key.setFechaInicio(inicio);
		key.setIdBus(idBus);
		key.setIdRuta(idRuta);
		
		Optional<Tmio1Servicio> service = serviciosServicio.getServicio(key);
		if (!service.isPresent())
			throw new IllegalArgumentException("Combinacion de parametros invalida");
		
		model.addAttribute("tmio1ServicioPK", service.get().getId());
		
		model.addAttribute("buses", busesServicio.getAllBuses());
		model.addAttribute("conductores", conductoresServicio.getAllConductores());
		model.addAttribute("rutas", rutasServicio.getAllRutas());
		
		return "servicios/edit";
	}
	
	@PostMapping("/edit/{idRuta}/{cedulaConductor}/{idBus}/{fechaInicio}/{fechaFin}")
	public String UpdateService(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin, @RequestParam(value = "action", required = true) String action,
	@Valid Tmio1ServicioPK servicePk, BindingResult bindingResult, Model model){
		
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("buses", busesServicio.getAllBuses());
				model.addAttribute("conductores", conductoresServicio.getAllConductores());
				model.addAttribute("rutas", rutasServicio.getAllRutas());
				servicePk.setFechaInicio(LocalDate.parse(fechaInicio));
				servicePk.setFechaFin(LocalDate.parse(fechaFin));
				
				return "servicios/edit";
			} else {
				LocalDate inicio = LocalDate.parse(fechaInicio);
				LocalDate fin = LocalDate.parse(fechaFin);
				
				Tmio1ServicioPK key = new Tmio1ServicioPK();
				
				key.setCedulaConductor(cedulaConductor);
				key.setFechaFin(fin);
				key.setFechaInicio(inicio);
				key.setIdBus(idBus);
				key.setIdRuta(idRuta);
				
				Tmio1Servicio service = new Tmio1Servicio();
				service.setId(servicePk);
				service.setTmio1Bus(busesServicio.getBus(servicePk.getIdBus()));
				service.setTmio1Conductore(conductoresServicio.getConductor(servicePk.getCedulaConductor()));
				service.setTmio1Ruta(rutasServicio.getRuta(servicePk.getIdRuta()));
				try {
					serviciosServicio.addServicio(service);
					Tmio1Servicio oldService = serviciosServicio.getServicio(key).get();
					if (!servicePk.equals(key))
						serviciosServicio.removeServicio(oldService);
				} catch (Exception e) {
					model.addAttribute("buses", busesServicio.getAllBuses());
					model.addAttribute("conductores", conductoresServicio.getAllConductores());
					model.addAttribute("rutas", rutasServicio.getAllRutas());
					servicePk.setFechaInicio(LocalDate.parse(fechaInicio));
					servicePk.setFechaFin(LocalDate.parse(fechaFin));
					
					model.addAttribute("exception", e.getMessage());
					return "servicios/edit";
				}
			}
		}
		
		return "redirect:/servicio";
	}
	
	@PostMapping("/filter-date")
	public String filterDate(@Validated(ValidateDate.class) Tmio1ServicioPK servicePk, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("servicios", serviciosServicio.getAllServicios());
		} else {
			model.addAttribute("servicios", getServicesFiltered(servicePk.getFechaInicio()));
		}
		
		return "servicios/index";
	}
	
	private Iterable<Tmio1Servicio> getServicesFiltered(LocalDate toCompare){
		List<Tmio1Servicio> toReturn = new ArrayList<Tmio1Servicio>();
		for (Tmio1Servicio serv: serviciosServicio.getAllServicios()) {
			if (serv.getId().getFechaInicio().compareTo(toCompare) <= 0 
					&& serv.getId().getFechaFin().compareTo(toCompare) >= 0)
				toReturn.add(serv);
		}
		return toReturn;
	}

}
