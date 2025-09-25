package com.kurocaelum.medicar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.dto.AgendaCreationDTO;
import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.mappers.AgendaMapper;
import com.kurocaelum.medicar.repositories.AgendaRepository;
import com.kurocaelum.medicar.services.exceptions.DatabaseException;
import com.kurocaelum.medicar.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repository;
	
	@Autowired
	private AgendaMapper agendaMapper;
	
	public List<Agenda> findAll() {
		return repository.findAll();
	}
	
	public List<AgendaDTO> findAllDto() {
		List<AgendaDTO> list = agendaMapper.mapAgendaToAgendaDTO(this.findAll());
		return list;
	}
	
	public Agenda findById(Long id) {
		Optional<Agenda> obj = repository.findById(id);
		return obj.get();
	}
	
	public Agenda insert(AgendaCreationDTO obj) {
		Agenda entity = new Agenda();
		agendaMapper.createFromDto(obj, entity);
		
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
	
	public Agenda update(Long id, Agenda obj) {
		try {
			Agenda entity = repository.findById(id).get();
			agendaMapper.update(obj, entity);
			
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
