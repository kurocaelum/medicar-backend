package com.kurocaelum.medicar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.dto.MedicoCreationDTO;
import com.kurocaelum.medicar.entities.Medico;
import com.kurocaelum.medicar.mappers.MedicoMapper;
import com.kurocaelum.medicar.repositories.MedicoRepository;
import com.kurocaelum.medicar.services.exceptions.DatabaseException;
import com.kurocaelum.medicar.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	@Autowired
	private MedicoMapper medicoMapper;
	
	public List<Medico> findAll(String search, List<Long> especialidades) {
		List<Medico> fullList = repository.findAll();
		List<Medico> list = new ArrayList<Medico>(fullList.size());

		for(Medico medico: fullList) {
			if(
				(search != null && !medico.getNome().toLowerCase().contains(search)) ||
				(especialidades != null && !especialidades.contains(medico.getEspecialidade().getId()))
			) continue;

			list.add(medico);
		}
		
		return list;
	}
	
	public Medico findById(Long id) {
		Optional<Medico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Medico insert(MedicoCreationDTO obj) {
		if(repository.existsByCrm(obj.crm()))
			throw new DatabaseException("CRM já cadastrado.");
		
		Medico entity = medicoMapper.createFromDto(obj);
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		try {
			if (!repository.existsById(id))
				throw new ResourceNotFoundException(id);
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Medico update(Long id, Medico obj) {
		try {
			Medico entity = repository.findById(id).get();
			medicoMapper.update(obj, entity);
			
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
