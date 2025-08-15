package com.kurocaelum.medicar.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
