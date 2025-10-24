package com.kurocaelum.medicar.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<AgendaDTO>> findAllDto(
		@RequestParam(required = false) List<Long> medico,
		@RequestParam(required = false) List<String> crm,
		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_inicio,
		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_final
	) {
		List<AgendaDTO> list = service.findAllDto(medico, crm, data_inicio, data_final);
		
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
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Agenda> updateDia(@PathVariable Long id, @RequestBody Agenda obj) {
		obj = service.updateDia(id, obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
