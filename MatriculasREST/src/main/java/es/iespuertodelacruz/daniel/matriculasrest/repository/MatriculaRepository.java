package es.iespuertodelacruz.daniel.matriculasrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;

public interface MatriculaRepository extends JpaRepository <Matricula, Integer> {

}
