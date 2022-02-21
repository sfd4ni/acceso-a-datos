package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Ejemplare;

public interface EjemplarRepository extends JpaRepository <Ejemplare, Integer> {
	
}
