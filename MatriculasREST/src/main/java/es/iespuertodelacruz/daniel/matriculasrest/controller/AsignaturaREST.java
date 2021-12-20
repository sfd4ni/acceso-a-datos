package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.dto.AlumnoDTO;
import es.iespuertodelacruz.daniel.matriculasrest.dto.AsignaturaDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.service.AlumnoService;
import es.iespuertodelacruz.daniel.matriculasrest.service.AsignaturaService;
import es.iespuertodelacruz.daniel.matriculasrest.service.MatriculaService;

@RestController
@RequestMapping("/api/asignatura")
public class AsignaturaREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AsignaturaService asignaturaService;
	@Autowired
	MatriculaService matriculaService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<AsignaturaDTO> l = new ArrayList<>();
		for(Asignatura asignatura : asignaturaService.findAll()) {
			AsignaturaDTO asignaturaDto = new AsignaturaDTO(asignatura);
			l.add(asignaturaDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
}
