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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.dto.AlumnoDTO;
import es.iespuertodelacruz.daniel.matriculasrest.dto.MatriculaDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
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
			
			return ResponseEntity.ok(optAlumno.get());
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
	
	@GetMapping("/{idalu}/matricula")
	public ResponseEntity<?> getAllMatriculas(@PathVariable("idalu") String idAlu) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			List<Matricula> list = optAlumno.get().getMatriculas();
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Alumno> optM = alumnoService.findById(id);
		if(optM.isPresent()) {
			alumnoService.deleteById(id);
			return ResponseEntity.ok("alumno borrado");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	
	@DeleteMapping("/{idAlu}/matricula/{idMatr}")
	public ResponseEntity<?> deleteMatricula(@PathVariable String idAlu, @PathVariable Integer idMatr){
		Optional<Alumno> optM = alumnoService.findById(idAlu);
		if(optM.isPresent()) {
			Optional<Matricula> optMatr = matriculaService.findById(idMatr);
			if (optMatr.isPresent()) {
				matriculaService.deleteById(idMatr);
				return ResponseEntity.ok("matricula borrada");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de matricula no existe en el alumno");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del alumno no existe");
		}

	}
	
	@PostMapping("/{idalu}/matricula")
	public ResponseEntity<?> saveMatricula(@PathVariable("idalu") String idAlu, 
			@RequestBody MatriculaDTO matriculaDto) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Matricula matricula = new Matricula();
			matricula.setAlumno(optAlumno.get());
			matricula.setAsignaturas(matriculaDto.getAsignaturas());
			matricula.setYear(matriculaDto.getYear());
			matriculaService.save(matricula);
			return ResponseEntity.ok(matricula);
		}else {
			return ResponseEntity.notFound().build();
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
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,
		@RequestBody Alumno aDTO){
		Optional<Alumno> optA = alumnoService.findById(id);
		if(optA.isPresent()) {
			Alumno a = optA.get();
			a.setNombre(aDTO.getNombre());
			a.setApellidos(aDTO.getApellidos());
			a.setFechanacimiento(aDTO.getFechanacimiento());
			return ResponseEntity.ok(alumnoService.save(a));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
}
