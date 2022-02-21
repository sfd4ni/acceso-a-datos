package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Libro;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.LibroRepository;

@Service
public class LibroService implements GenericService<Libro, Integer> {
	@Autowired
	LibroRepository libroRepository;

	@Override
	public Iterable<Libro> findAll() {
		return libroRepository.findAll();
	}

	@Override
	public Page<Libro> findAll(Pageable pageable) {
		return libroRepository.findAll(pageable);
	}

	@Override
	public Optional<Libro> findById(Integer id) {
		return libroRepository.findById(id);
	}

	@Override
	public Libro save(Libro object) {
		return libroRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		libroRepository.deleteById(id);
		
	}

	@Override
	public void delete(Libro object) {
		libroRepository.delete(object);
		
	}
}
