package es.iespuertodelacruz.daniel.bibliotecarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Autor;



public interface AutorRepository extends JpaRepository <Autor, Integer> {
	
}
