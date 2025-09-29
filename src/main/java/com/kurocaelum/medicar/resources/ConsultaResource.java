package com.kurocaelum.medicar.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurocaelum.medicar.dto.ConsultaDTO;
import com.kurocaelum.medicar.dto.ConsultaUpdateDTO;
import com.kurocaelum.medicar.entities.Consulta;
import com.kurocaelum.medicar.services.ConsultaService;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaResource {
	
	@Autowired
	private ConsultaService service;
	
	@GetMapping
	public ResponseEntity<List<ConsultaDTO>> findAllDto() {
		List<ConsultaDTO> list = service.findAllDto();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/details")
	public ResponseEntity<List<Consulta>> findAll() {
		List<Consulta> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Consulta> findById(@PathVariable Long id) {
		Consulta obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<ConsultaDTO> marcarConsulta(@RequestBody ConsultaUpdateDTO obj) {
		ConsultaDTO entity = service.marcarConsulta(obj);
		
		return ResponseEntity.ok().body(entity);
	}
	
	
}
