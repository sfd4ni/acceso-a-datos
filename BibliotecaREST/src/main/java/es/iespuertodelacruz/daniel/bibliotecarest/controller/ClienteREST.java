package es.iespuertodelacruz.daniel.bibliotecarest.controller;

import java.math.BigInteger;
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

import es.iespuertodelacruz.daniel.bibliotecarest.dto.ClienteDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.dto.ClienteGET;
import es.iespuertodelacruz.daniel.bibliotecarest.dto.EjemplarDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.dto.PrestamoDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.dto.ClienteDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Prestamo;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.service.ClienteService;
import es.iespuertodelacruz.daniel.bibliotecarest.service.EjemplarService;
import es.iespuertodelacruz.daniel.bibliotecarest.service.PrestamoService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	ClienteService clienteService;
	@Autowired
	EjemplarService ejemplarService;
	@Autowired
	PrestamoService prestamoService;

	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Cliente> l = (List<Cliente>) clienteService.findAll();
		List<ClienteGET> clientes = new ArrayList<>();
		for (Cliente cliente : l) {
			clientes.add(new ClienteGET(cliente));
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
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
			return ResponseEntity.ok(new ClienteGET(optCliente.get()));
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
	
	@DeleteMapping("/{idCli}/prestamo/{idPrest}")
	public ResponseEntity<?> deletePrestamo(
			@PathVariable Integer idCli,
			@PathVariable Integer idPrest
			) {
		Optional<Cliente> optM = clienteService.findById(idCli);
		if(optM.isPresent()) {
			Optional<Prestamo> optMatr = prestamoService.findById(idPrest);
			if (optMatr.isPresent()) {
				prestamoService.deleteById(idPrest);
				return ResponseEntity.ok("prestamo borrado");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de prestamo no existe en el ejemplar");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del alumno no existe");
		}

	}
	
	@GetMapping("/{idCli}/prestamo/{idPrest}")
	public ResponseEntity<?> getPrestamoById(
			@PathVariable Integer idCli,
			@PathVariable Integer idPrest
			) {
		
		Optional<Cliente> optM = clienteService.findById(idCli);
		if(optM.isPresent()) {
			Optional<Prestamo> optMatr = prestamoService.findById(idPrest);
			if (optMatr.isPresent()) {
				return ResponseEntity.ok(new PrestamoDTO(optMatr.get()));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de ejemplar no existe en el cliente");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del cliente no existe");
		}
	}
	

	@PutMapping("/{idCli}/prestamo/{idPrest}")
	public ResponseEntity<?> updateEjemplar(
			@PathVariable Integer idCli,
			@PathVariable Integer idPrest,
			@RequestBody PrestamoDTO prestamoDto){
		Optional<Cliente> optOp = clienteService.findById(idCli);
		if(optOp.isPresent()) {
			Optional<Prestamo> optEjem = prestamoService.findById(idPrest);
			if(optEjem.isPresent()) {
				Prestamo prestamo = optEjem.get();
				if (prestamoDto.getFechadevolucion() != null) {
					prestamo.setFechadevolucion(BigInteger.valueOf(prestamoDto.getFechadevolucion().getTime()));
				}
			
				prestamo.setFechaprestamo(BigInteger.valueOf(prestamoDto.getFechaprestamo().getTime()));
				prestamo.setEjemplare(prestamoDto.getEjemplar());
				
				return ResponseEntity.ok(prestamoService.save(prestamo));
			} else {
				return
						ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del prestamo no existe");
			}
			
		}else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del cliente no existe");
		}
	}
	
	@PostMapping("/{idCli}/prestamo")
	public ResponseEntity<?> savePrestamo(
			@PathVariable Integer idCli,
			@RequestBody PrestamoDTO prestamoDto) {
		Optional<Cliente> optOp = clienteService.findById(idCli);
		if(optOp.isPresent()) {
			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(optOp.get());
			if (prestamoDto.getFechadevolucion() != null) {
				prestamo.setFechadevolucion(BigInteger.valueOf(prestamoDto.getFechadevolucion().getTime()));
			}
			
			prestamo.setFechaprestamo(BigInteger.valueOf(prestamoDto.getFechaprestamo().getTime()));
			prestamo.setEjemplare(prestamoDto.getEjemplar());
			Prestamo prestamoC = null;
			try {
				prestamoC = prestamoService.save(prestamo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (prestamoC != null) {
				return new ResponseEntity<>(prestamoC, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Error al guardar su nuevo prestamo.", HttpStatus.CONFLICT);
			}
		} else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del cliente no existe");
		}
		
	}
}
