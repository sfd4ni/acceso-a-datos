package es.iespuertodelacruz.daniel.bibliotecarest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.bibliotecarest.entity.Cliente;
import es.iespuertodelacruz.daniel.bibliotecarest.repository.ClienteRepository;

@Service
public class ClienteService implements GenericService<Cliente, Integer> {
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	public Optional<Cliente> findById(Integer id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente save(Cliente object) {
		return clienteRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		clienteRepository.deleteById(id);
		
	}

	@Override
	public void delete(Cliente object) {
		clienteRepository.delete(object);
		
	}
}
