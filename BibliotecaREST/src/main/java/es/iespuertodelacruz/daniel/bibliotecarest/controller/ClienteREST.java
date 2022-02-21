package es.iespuertodelacruz.daniel.bibliotecarest.controller;

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

import es.iespuertodelacruz.daniel.bibliotecarest.dto.ClienteDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	ClienteService clienteService;
	

	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Cliente> l = (List<Cliente>) clienteService.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable Integer id
			) {
		Optional<Cliente> optM = clienteService.findById(id);
		if(optM.isPresent()) {
			clienteService.deleteById(id);
			return ResponseEntity.ok("cliente borrada");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(
			@PathVariable("id") Integer id
			) {
		
		Optional<Cliente> optCliente = clienteService.findById(id);
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Integer id,
			@RequestBody ClienteDTO clienteIn){
		Optional<Cliente> optOp = clienteService.findById(id);
		if(optOp.isPresent()) {
			Cliente cliente = optOp.get();
			cliente.setNombre(clienteIn.getNombre());
			cliente.setApellidos(clienteIn.getApellidos());
			cliente.setDireccion(clienteIn.getDireccion());
			return ResponseEntity.ok(clienteService.save(cliente));
		}else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveCliente(
			@RequestBody Cliente clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setNombre(clienteDto.getNombre());
		cliente.setApellidos(clienteDto.getApellidos());
		cliente.setDireccion(clienteDto.getDireccion());
		Cliente clienteC = null;
		try {
			clienteC = clienteService.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (clienteC != null) {
			return new ResponseEntity<>(clienteC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
		}
		
	}
}
