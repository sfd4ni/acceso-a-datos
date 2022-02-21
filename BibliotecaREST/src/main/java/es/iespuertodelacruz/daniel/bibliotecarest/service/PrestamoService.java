package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Prestamo;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.PrestamoRepository;

@Service
public class PrestamoService implements GenericService<Prestamo, Integer> {
	@Autowired
	PrestamoRepository prestamoRepository;

	@Override
	public Iterable<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public Page<Prestamo> findAll(Pageable pageable) {
		return prestamoRepository.findAll(pageable);
	}

	@Override
	public Optional<Prestamo> findById(Integer id) {
		return prestamoRepository.findById(id);
	}

	@Override
	public Prestamo save(Prestamo object) {
		return prestamoRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		prestamoRepository.deleteById(id);
		
	}

	@Override
	public void delete(Prestamo object) {
		prestamoRepository.delete(object);
		
	}
}
