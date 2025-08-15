package com.kurocaelum.medicar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.entities.Medico;
import com.kurocaelum.medicar.repositories.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	public List<Medico> findAll() {
		return repository.findAll();
	}
	
	public Medico findById(Long id) {
		Optional<Medico> obj = repository.findById(id);
		return obj.get();
	}
	
	public Medico insert(Medico obj) {
		return repository.save(obj);
	}

}
