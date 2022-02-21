package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;

public interface LibroRepository extends JpaRepository <Libro, Integer> {
	
}
