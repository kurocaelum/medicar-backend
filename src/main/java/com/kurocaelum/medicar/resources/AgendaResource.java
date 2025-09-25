package com.kurocaelum.medicar.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kurocaelum.medicar.dto.AgendaCreationDTO;
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
	public ResponseEntity<Agenda> findById(@PathVariable Long id) {
		Agenda obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Agenda> insert(@RequestBody AgendaCreationDTO obj) {
		Agenda entity = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(entity);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
//	TODO precisa do objeto Agenda inteiro, não basta AgendaDTO? (Agenda retornável por /details)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Agenda> update(@PathVariable Long id, @RequestBody Agenda obj) {
		obj = service.update(id, obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
