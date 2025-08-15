package com.kurocaelum.medicar.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurocaelum.medicar.entities.Consulta;
import com.kurocaelum.medicar.services.ConsultaService;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaResource {
	
	@Autowired
	private ConsultaService service;
	
	@GetMapping
	public ResponseEntity<List<Consulta>> findAll() {
		List<Consulta> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Consulta> findById(Long id) {
		Consulta obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
