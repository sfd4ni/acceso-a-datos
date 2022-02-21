package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository <Prestamo, Integer> {
	
}
