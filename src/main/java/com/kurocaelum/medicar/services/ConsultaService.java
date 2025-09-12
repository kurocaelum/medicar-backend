package com.kurocaelum.medicar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.dto.ConsultaDTO;
import com.kurocaelum.medicar.entities.Consulta;
import com.kurocaelum.medicar.mappers.ConsultaMapper;
import com.kurocaelum.medicar.repositories.ConsultaRepository;
import com.kurocaelum.medicar.services.exceptions.DatabaseException;
import com.kurocaelum.medicar.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repository;
	
	@Autowired
	private ConsultaMapper consultaMapper;
	
	public List<Consulta> findAll() {
		return repository.findAll();
	}
	
	public List<ConsultaDTO> findAllDto() {
		List<ConsultaDTO> list = consultaMapper.mapConsultaToConsultaDTO(this.findAll());
		return list;
	}
	
	public Consulta findById(Long id) {
		Optional<Consulta> obj = repository.findById(id);
		return obj.get();
	}
	
	public Consulta insert(Consulta obj) {
		return repository.save(obj);
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
	
	public Consulta update(Long id, Consulta obj) {
		try {
			Consulta entity = repository.findById(id).get();
			consultaMapper.update(obj, entity);
			
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
