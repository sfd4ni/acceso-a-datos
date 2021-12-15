package es.iespuertodelacruz.jc.cambiomonedas.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jc.cambiomonedas.dto.MonedasDTO;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;
import es.iespuertodelacruz.jc.cambiomonedas.service.MonedasService;

@RestController
@RequestMapping("/api/monedas")
public class MonedasREST {

	@Autowired
	private MonedasService monedasService;
	

	
	@GetMapping
	public Collection<MonedasDTO> getAll(){
		List l = new ArrayList<MonedasDTO>();
		for(Monedas m: monedasService.findAll()) {
			l.add(new MonedasDTO(m));
		}
		//return new ResponseEntity<>(l, HttpStatus.OK);
		return 	l;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMonedaById(@PathVariable("id") Integer id) {
		
		Optional<Monedas> optMonedas = monedasService.findById(id);
		if(optMonedas.isPresent()) {
			
			return ResponseEntity.ok(new MonedasDTO(optMonedas.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody MonedasDTO mDTO){
		Monedas m = new Monedas();
		m.setNombre(mDTO.getNombre());
		m.setPais(mDTO.getPais());
		monedasService.save(m);
		
		return ResponseEntity.ok().body(new MonedasDTO(m));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Monedas> optM = monedasService.findById(id);
		if(optM.isPresent()) {
			monedasService.deleteById(id);
			return ResponseEntity.ok("moneda borrada");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MonedasDTO mDTO){
		Optional<Monedas> optM = monedasService.findById(id);
		if(optM.isPresent()) {
			Monedas m = optM.get();
			m.setNombre(mDTO.getNombre());
			m.setPais(mDTO.getPais());
			return ResponseEntity.ok(monedasService.save(m));
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}	
	
	
}
