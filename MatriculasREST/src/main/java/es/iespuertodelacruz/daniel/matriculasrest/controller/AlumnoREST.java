package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.service.AlumnoService;
import es.iespuertodelacruz.jc.cambiomonedas.dto.MonedasDTO;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AlumnoService alumnoService;
	@GetMapping
	public List<Alumno> getAll() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		//logger.info("si queremos hacer debug por ejemplo");
		alumnoService
		.findAll()
		.forEach(a -> alumnos.add((Alumno) a) );
		return alumnos;
	}
	@GetMapping
	public Collection<MonedasDTO> getAll(){
		List l = new ArrayList<MonedasDTO>();
		for(Monedas m: monedasService.findAll()) {
			l.add(new MonedasDTO(m));
		}
		//return new ResponseEntity<>(l, HttpStatus.OK);
		return 	l;
	}
	@PostMapping("/{id}/matricula") 
	public ResponseEntity<?> saveMatricula (
			@PathVariable("id") Integer id,
			@RequestBody Matricula matricula){
		return null;
	}
}
