package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.dto.AlumnoDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.service.AlumnoService;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List l = new ArrayList<AlumnoDTO>();
		for(Alumno alumno : alumnoService.findAll()) {
			AlumnoDTO alumnoDto = new AlumnoDTO(alumno);
			l.add(alumnoDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnoService.findById(id);
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(new AlumnoDTO(optAlumno.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	public ResponseEntity<?> saveAlumno(
			@RequestBody AlumnoDTO alumnoDto) {
		Alumno alumno = new Alumno();
		alumno.setDni(alumnoDto.getDni());
		alumno.setNombre(alumno.getNombre());
		alumno.setApellidos(alumnoDto.getApellidos());
		alumno.setFechanacimiento(alumno.getFechanacimiento());
		alumnoService.save(alumno);
		return new ResponseEntity<>(alumno, HttpStatus.OK);
	}
	@PostMapping("/{id}/matricula") 
	public ResponseEntity<?> saveMatricula (
			@PathVariable("id") Integer id,
			@RequestBody Matricula matricula) {
		return null;
	}
}
