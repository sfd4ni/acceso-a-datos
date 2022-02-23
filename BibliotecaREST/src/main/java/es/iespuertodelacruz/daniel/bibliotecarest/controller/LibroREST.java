package es.iespuertodelacruz.daniel.bibliotecarest.controller;

import java.util.ArrayList;
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

import es.iespuertodelacruz.daniel.bibliotecarest.dto.EjemplarDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.dto.LibroDTO;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;
import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;
import es.iespuertodelacruz.daniel.bibliotecarest.service.AutorService;
import es.iespuertodelacruz.daniel.bibliotecarest.service.EjemplarService;
import es.iespuertodelacruz.daniel.bibliotecarest.service.LibroService;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	LibroService libroService;
	@Autowired
	AutorService autorService;
	@Autowired
	EjemplarService ejemplarService;
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<Libro> l = (List<Libro>) libroService.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable Integer id
			) {
		Optional<Libro> optM = libroService.findById(id);
		if(optM.isPresent()) {
			libroService.deleteById(id);
			return ResponseEntity.ok("libro borrada");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLibroById(
			@PathVariable("id") Integer id
			) {
		
		Optional<Libro> optLibro = libroService.findById(id);
		if(optLibro.isPresent()) {
			return ResponseEntity.ok(optLibro.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Integer id,
			@RequestBody LibroDTO libroIn){
		Optional<Libro> optOp = libroService.findById(id);
		if(optOp.isPresent()) {
			Libro libro = optOp.get();
			libro.setEditorial(libroIn.getEditorial());
			libro.setTitulo(libroIn.getTitulo());
			List<Autor> autores = new ArrayList<>();
			if (libroIn.getAutores() != null) {
				for (Autor autor : libroIn.getAutores()) {
					Optional<Autor> optAutor = autorService.findById(autor.getAutorid());
					if (optAutor.isPresent()) {
						autores.add(optAutor.get());
					}
				}
			}
			libro.setAutores(autores);
			
			return ResponseEntity.ok(libroService.save(libro));
		}else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveLibro(
			@RequestBody Libro libroDto) {
		Libro libro = new Libro();
		libro.setEditorial(libroDto.getEditorial());
		libro.setTitulo(libroDto.getTitulo());
		List<Autor> autores = new ArrayList<>();
		if (libroDto.getAutores() != null) {
			for (Autor autor : libroDto.getAutores()) {
				Optional<Autor> optAutor = autorService.findById(autor.getAutorid());
				if (optAutor.isPresent()) {
					autores.add(optAutor.get());
				}
			}
		}
		libro.setAutores(autores);
		Libro libroC = null;
		try {
			libroC = libroService.save(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (libroC != null) {
			return new ResponseEntity<>(libroC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
		}
		
	}
	

	@DeleteMapping("/{idLib}/ejemplar/{idEjem}")
	public ResponseEntity<?> deleteEjemplar(
			@PathVariable Integer idLib,
			@PathVariable Integer idEjem
			) {
		Optional<Libro> optM = libroService.findById(idLib);
		if(optM.isPresent()) {
			Optional<Ejemplare> optMatr = ejemplarService.findById(idEjem);
			if (optMatr.isPresent()) {
				ejemplarService.deleteById(idEjem);
				return ResponseEntity.ok("matricula borrada");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de matricula no existe en el alumno");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del alumno no existe");
		}

	}
	
	@GetMapping("/{idLib}/ejemplar/{idEjem}")
	public ResponseEntity<?> getEjemplarById(
			@PathVariable Integer idLib,
			@PathVariable Integer idEjem
			) {
		
		Optional<Libro> optM = libroService.findById(idLib);
		if(optM.isPresent()) {
			Optional<Ejemplare> optMatr = ejemplarService.findById(idEjem);
			if (optMatr.isPresent()) {
				return ResponseEntity.ok(optMatr.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de ejemplar no existe en el libro");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del libro no existe");
		}
	}
	

	@PutMapping("/{idLib}/ejemplar/{idEjem}")
	public ResponseEntity<?> updateEjemplar(
			@PathVariable Integer idLib,
			@PathVariable Integer idEjem,
			@RequestBody EjemplarDTO ejemplarIn){
		Optional<Libro> optOp = libroService.findById(idLib);
		if(optOp.isPresent()) {
			Optional<Ejemplare> optEjem = ejemplarService.findById(idEjem);
			if(optEjem.isPresent()) {
				Ejemplare ejemplar = optEjem.get();
				ejemplar.setLocalizacion(ejemplarIn.getLocalizacion());
				
				return ResponseEntity.ok(ejemplarService.save(ejemplar));
			} else {
				return
						ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del ejemplar no existe");
			}
			
		}else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
	@PostMapping("/{idLib}/ejemplar")
	public ResponseEntity<?> saveEjemplar(
			@PathVariable Integer idLib,
			@RequestBody EjemplarDTO ejemplarDto) {
		Optional<Libro> optOp = libroService.findById(idLib);
		if(optOp.isPresent()) {
			Ejemplare ejemplar = new Ejemplare();
			ejemplar.setLibro(optOp.get());
			ejemplar.setLocalizacion(ejemplarDto.getLocalizacion());
			Ejemplare ejemplarC = null;
			try {
				ejemplarC = ejemplarService.save(ejemplar);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ejemplarC != null) {
				return new ResponseEntity<>(ejemplarC, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
			}
		} else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del libro no existe");
		}
		
	}
}
