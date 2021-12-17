package es.iespuertodelacruz.daniel.matriculasrest.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.dto.AlumnoDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.service.AlumnoService;
import es.iespuertodelacruz.daniel.matriculasrest.service.MatriculaService;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	MatriculaService matriculaService;
	
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
	
	@GetMapping("/{idalu}/matricula/{idmatr}")
	public ResponseEntity<?> getMatriculaById(@PathVariable("idalu") String idAlu,
			@PathVariable("idmatr") Integer idMatr) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Optional<Matricula> optMatr = matriculaService.findById(idMatr);
			if (optMatr.isPresent()) {
				return ResponseEntity.ok(optMatr.get());
			} else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Alumno> optM = alumnoService.findById(id);
		if(optM.isPresent()) {
			alumnoService.deleteById(id);
			return ResponseEntity.ok("moneda borrada");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	@PostMapping
	public ResponseEntity<?> saveAlumno(
			@RequestBody AlumnoDTO alumnoDto) {
		Alumno alumno = new Alumno();
		alumno.setDni(alumnoDto.getDni());
		alumno.setNombre(alumnoDto.getNombre());
		alumno.setApellidos(alumnoDto.getApellidos());
		alumno.setFechanacimiento(alumnoDto.getFechaNacimiento());
		alumnoService.save(alumno);
		return new ResponseEntity<>(alumno, HttpStatus.OK);
	}
	/*
	@PostMapping("/{idAlu}/matricula") 
	public ResponseEntity<?> saveMatricula (
			@PathVariable("idAlu") String idAlu,
			@RequestBody Matricula matricula) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Optional<Matricula> optMatr = matriculaService.findById(idMatr);
			if (optMatr.isPresent()) {
				return ResponseEntity.ok(optMatr.get());
			} else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}*/
}
