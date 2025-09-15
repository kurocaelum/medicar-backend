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

import com.kurocaelum.medicar.dto.MedicoCreationDTO;
import com.kurocaelum.medicar.entities.Medico;
import com.kurocaelum.medicar.services.MedicoService;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoResource {
	
	@Autowired
	private MedicoService service;
	
	@GetMapping
	public ResponseEntity<List<Medico>> findAll() {
		List<Medico> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Medico> findById(Long id) {
		Medico obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Medico> insert(@RequestBody MedicoCreationDTO obj) {
		Medico entity = service.insert(obj);
		
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
	public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody Medico obj) {
		obj = service.update(id, obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
