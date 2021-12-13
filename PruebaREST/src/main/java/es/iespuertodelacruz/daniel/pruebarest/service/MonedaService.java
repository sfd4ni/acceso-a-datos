package es.iespuertodelacruz.daniel.pruebarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.pruebarest.entity.Moneda;
import es.iespuertodelacruz.daniel.pruebarest.repository.MonedaRepository;

@Service
public class MonedaService implements GenericService<Moneda, Integer> {

	@Autowired
	private MonedaRepository monedaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Moneda> findAll() {
		return monedaRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Moneda> findAll(Pageable pageable) {
		return monedaRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Moneda> findById(Integer id) {
		return monedaRepository.findById(id);
	}

	@Override
	@Transactional
	public Moneda save(Moneda objeto) {
		return monedaRepository.save(objeto);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		monedaRepository.deleteById(id);
		
	}

}
