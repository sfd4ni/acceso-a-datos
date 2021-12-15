package es.iespuertodelacruz.jc.cambiomonedas.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import es.iespuertodelacruz.jc.cambiomonedas.dto.HistoricocambioeuroDTO;
import es.iespuertodelacruz.jc.cambiomonedas.dto.MonedasDTO;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;
import es.iespuertodelacruz.jc.cambiomonedas.service.HistoricocambioeuroService;
import es.iespuertodelacruz.jc.cambiomonedas.service.MonedasService;

@RestController
@RequestMapping("/api/historicocambioeuro")
public class HistoricocambioeuroREST {

	@Autowired
	private HistoricocambioeuroService historicocambioeuroService;
	
	@Autowired
	private MonedasService monedasService;
	
	
	@GetMapping
	public Collection<HistoricocambioeuroDTO> getAll(){
		List l = new ArrayList<Historicocambioeuro>();
		for(Historicocambioeuro m: historicocambioeuroService.findAll()) {
			l.add(new HistoricocambioeuroDTO(m));
		}
		//return new ResponseEntity<>(l, HttpStatus.OK);
		return 	l;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getHisoricocambioeuroById(@PathVariable("id") Integer id) {
		
		Optional<Historicocambioeuro> optHistorico = historicocambioeuroService.findById(id);
		
		if(optHistorico.isPresent()) {
			
			return ResponseEntity.ok(new HistoricocambioeuroDTO(optHistorico.get()));
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody HistoricocambioeuroDTO hDTO){
		Historicocambioeuro h = new Historicocambioeuro();
		h.setEquivalenteeuro(hDTO.getEquivalenteeuro());
		h.setFecha(hDTO.getFecha() );
		Optional<Monedas> optM= monedasService.findById(hDTO.getFkidmoneda());
		if( optM.isPresent()) {
			h.setFkidmoneda(optM.get());
			historicocambioeuroService.save(h);
			return ResponseEntity.ok().body(new HistoricocambioeuroDTO(h));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no existe");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Historicocambioeuro> optM = historicocambioeuroService.findById(id);
		if(optM.isPresent()) {
			historicocambioeuroService.deleteById(id);
			return ResponseEntity.ok("registro de cambio con euro borrado");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody HistoricocambioeuroDTO hDTO){
		Optional<Historicocambioeuro> optH = historicocambioeuroService.findById(id);
		if(optH.isPresent()) {
			Historicocambioeuro h = optH.get();
			h.setEquivalenteeuro(hDTO.getEquivalenteeuro());
			h.setFecha(hDTO.getFecha() );
			Optional<Monedas> optM= monedasService.findById(hDTO.getFkidmoneda());
			if( optM.isPresent()) {
				h.setFkidmoneda(optM.get());
				historicocambioeuroService.save(h);
				return ResponseEntity.ok().body(new HistoricocambioeuroDTO(h));
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no existe");
			}			
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}	
}
