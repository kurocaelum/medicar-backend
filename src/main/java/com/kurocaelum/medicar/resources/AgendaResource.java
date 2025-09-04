package com.kurocaelum.medicar.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.services.AgendaService;

@RestController
@RequestMapping(value = "/agendas")
public class AgendaResource {
	
	@Autowired
	private AgendaService service;
	
	@GetMapping
	public ResponseEntity<List<AgendaDTO>> findAllDto() {
		List<AgendaDTO> list = service.findAllDto();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/details")
	public ResponseEntity<List<Agenda>> findAll() {
		List<Agenda> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Agenda> findById(Long id) {
		Agenda obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
