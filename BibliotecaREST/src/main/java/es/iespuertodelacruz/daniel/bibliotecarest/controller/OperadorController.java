package es.iespuertodelacruz.daniel.bibliotecarest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Operadore;
import es.iespuertodelacruz.daniel.bibliotecarest.service.OperadorService;




public class OperadorController {
	@RestController
	@RequestMapping("/api/v1/operador")
	public class OperadoreREST {
		//private Logger logger = (Logger) LoggerFactory.logger(getClass());
		@Autowired
		OperadorService operadorService;
		

		@GetMapping
		public ResponseEntity<?> getAll(){
			List<Operadore> l = (List<Operadore>) operadorService.findAll();
			return new ResponseEntity<>(l, HttpStatus.OK);
		}
		

		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete(
				@PathVariable Integer id
				) {
			Optional<Operadore> optM = operadorService.findById(id);
			if(optM.isPresent()) {
				operadorService.deleteById(id);
				return ResponseEntity.ok("operador borrada");
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
			}

		}
		
		@GetMapping("/{id}")
		public ResponseEntity<?> getOperadorById(
				@PathVariable("id") Integer id
				) {
			
			Optional<Operadore> optOperadore = operadorService.findById(id);
			if(optOperadore.isPresent()) {
				return ResponseEntity.ok(optOperadore.get());
			}else {
				return ResponseEntity.notFound().build();
			}
		}
		

		@PutMapping("/{id}")
		public ResponseEntity<?> update(
				@PathVariable Integer id,
				@RequestBody Operadore operadorIn){
			Optional<Operadore> optOp = operadorService.findById(id);
			if(optOp.isPresent()) {
				Operadore operador = optOp.get();
				operador.setNick(operadorIn.getNick());
				operador.setPassword(BCrypt.hashpw(operadorIn.getPassword(), BCrypt.gensalt(10)));
				return ResponseEntity.ok(operadorService.save(operador));
			}else {
				return
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
			}
		}
		
		@PostMapping
		public ResponseEntity<?> saveOperador(
				@RequestBody Operadore operadorDto) {
			Operadore operador = new Operadore();
			operador.setNick(operadorDto.getNick());
			operador.setPassword(BCrypt.hashpw(operadorDto.getPassword(), BCrypt.gensalt(10)));
			Operadore operadorC = null;
			try {
				operadorC = operadorService.save(operador);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (operadorC != null) {
				return new ResponseEntity<>(operadorC, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
			}
			
		}
	}
}
