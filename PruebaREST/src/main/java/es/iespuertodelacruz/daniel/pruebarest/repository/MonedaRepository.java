package es.iespuertodelacruz.daniel.pruebarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.pruebarest.entity.Moneda;

public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
}
