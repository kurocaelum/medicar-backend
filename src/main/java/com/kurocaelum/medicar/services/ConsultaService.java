package com.kurocaelum.medicar.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kurocaelum.medicar.dto.ConsultaDTO;
import com.kurocaelum.medicar.dto.ConsultaUpdateDTO;
import com.kurocaelum.medicar.entities.Agenda;
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
	private AgendaService agendaService;
	
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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
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

	public void desmarcarConsulta(Long id) {
		try {
			Consulta obj = this.findById(id);
			
			if(obj.getDataAgendamento() == null)
				throw new DatabaseException("Essa consulta não possui agendamento marcado.");
			
			if(obj.getAgenda().getDia().isBefore(LocalDate.now()))
				throw new DatabaseException("Não é possível desmarcar consulta realizada em data passada.");

			// Exceção para proibir desmarcar consulta que ocorreu no mesmo dia em horário mais cedo 						
			if(obj.getAgenda().getDia().equals(LocalDate.now()) && obj.getHorario().isBefore(LocalTime.now()))
				throw new DatabaseException("Não é possível desmarcar consulta realizada em data passada.");
			
			obj.setDataAgendamento(null);
			repository.save(obj);
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
	
	public ConsultaDTO marcarConsulta(ConsultaUpdateDTO obj) {
		if(!agendaService.existsById(obj.agenda_id())) 
			throw new DatabaseException("Agenda não encontrada.");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
		LocalTime horario = LocalTime.parse(obj.horario(), formatter);
		
		if(horario.isBefore(LocalTime.now()))
			throw new DatabaseException("Horário especificado já passou.");
		
		Agenda agenda = agendaService.findById(obj.agenda_id());
		
		if(agenda.getDia().isBefore(LocalDate.now()))
			throw new DatabaseException("Dia da agenda especificada já passou.");
		
		Consulta consulta = agenda.getHorarios().stream().filter(h -> h.getHorario().equals(horario)).findFirst().get();
		
		if(consulta.getDataAgendamento() != null)
			throw new DatabaseException("Consulta já marcada.");
		
		consulta.setDataAgendamento(LocalDateTime.now());
		this.update(consulta.getId(), consulta);
		
		return consultaMapper.map(consulta);
	}
	
}
