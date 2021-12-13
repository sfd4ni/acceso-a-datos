package es.iespuertodelacruz.daniel.pruebarest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.pruebarest.entity.Moneda;
import es.iespuertodelacruz.daniel.pruebarest.service.MonedaService;

@RestController
@RequestMapping("/api/monedas")
public class MonedaREST {
	@Autowired
	MonedaService monedaService;
	@GetMapping
	public List<Moneda> getAll(){
	ArrayList<Moneda> monedas = new ArrayList<Moneda>();
	//logger.info("si queremos hacer debug por ejemplo");
	monedaService
	.findAll()
	.forEach(m -> monedas.add((Moneda) m) );
	return monedas;
	}
}
