package com.kurocaelum.medicar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.mappers.AgendaMapper;
import com.kurocaelum.medicar.repositories.AgendaRepository;

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
	
	public Agenda insert(Agenda obj) {
		return repository.save(obj);
	}
	
}
