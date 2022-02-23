package es.iespuertodelacruz.daniel.bibliotecarest.controller;

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

import es.iespuertodelacruz.daniel.bibliotecarest.dto.AutorDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;
import es.iespuertodelacruz.daniel.bibliotecarest.service.AutorService;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AutorService autorService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Autor> l = (List<Autor>) autorService.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Autor> optM = autorService.findById(id);
		if (optM.isPresent()) {
			autorService.deleteById(id);
			return ResponseEntity.ok("autor borrada");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAutorById(@PathVariable("id") Integer id) {

		Optional<Autor> optAutor = autorService.findById(id);
		if (optAutor.isPresent()) {
			return ResponseEntity.ok(optAutor.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AutorDTO autorIn) {
		Optional<Autor> optOp = autorService.findById(id);
		if (optOp.isPresent()) {
			Autor autor = optOp.get();
			autor.setNombre(autorIn.getNombre());
			autor.setApellidos(autorIn.getApellidos());
			autor.setNacionalidad(autorIn.getNacionalidad());
			return ResponseEntity.ok(autorService.save(autor));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}

	@PostMapping
	public ResponseEntity<?> saveAutor(@RequestBody Autor autorDto) {
		Autor autor = new Autor();
		autor.setNombre(autorDto.getNombre());
		autor.setApellidos(autorDto.getApellidos());
		autor.setNacionalidad(autorDto.getNacionalidad());
		Autor autorC = null;
		try {
			autorC = autorService.save(autor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (autorC != null) {
			return new ResponseEntity<>(autorC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
		}

	}
}
