package es.iespuertodelacruz.daniel.pruebarest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.pruebarest.entity.Historicocambioeuro;
import es.iespuertodelacruz.daniel.pruebarest.service.HistoricoService;
/*
@RestController
@RequestMapping("/api/historicos")
*/
public class HistoricoREST {
	@Autowired
	HistoricoService historicoService;
	@GetMapping
	public List<Historicocambioeuro> getAll(){
	ArrayList<Historicocambioeuro> historicos = new ArrayList<Historicocambioeuro>();
	//logger.info("si queremos hacer debug por ejemplo");
	historicoService
	.findAll()
	.forEach(h -> historicos.add((Historicocambioeuro) h) );
	return historicos;
	}
}
