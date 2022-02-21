package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
	
}
