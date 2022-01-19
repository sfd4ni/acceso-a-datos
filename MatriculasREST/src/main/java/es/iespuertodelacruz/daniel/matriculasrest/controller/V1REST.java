package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.matriculasrest.dto.AsignaturaDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.service.AsignaturaService;
import es.iespuertodelacruz.daniel.matriculasrest.service.MatriculaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/asignatura")
public class V1REST {
	@Autowired
	AsignaturaService asignaturaService;
	@Autowired
	MatriculaService matriculaService;
	
	@ApiOperation(value = "Devuelve una lista de AsignaturaDTO", 
		    response = AsignaturaDTO.class,
		    responseContainer = "List")
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<AsignaturaDTO> l = new ArrayList<>();
		for(Asignatura asignatura : asignaturaService.findAll()) {
			AsignaturaDTO asignaturaDto = new AsignaturaDTO(asignatura);
			l.add(asignaturaDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Devuelve una Asignatura a partir de su id", 
		    response = Asignatura.class)
	@GetMapping("/{id}")
	public ResponseEntity<?> getAsignaturaById(
			@ApiParam(value = "Id de la asignatura a buscar.", required = true)
			@PathVariable("id") Integer id
			) {
		
		Optional<Asignatura> optAsignatura = asignaturaService.findById(id);
		if(optAsignatura.isPresent()) {
			return ResponseEntity.ok(optAsignatura.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
