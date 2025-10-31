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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kurocaelum.medicar.entities.Especialidade;
import com.kurocaelum.medicar.services.EspecialidadeService;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeResource {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public ResponseEntity<List<Especialidade>> findAll(
		@RequestParam(required = false) String search
	) {
        List<Especialidade> list;

		if(search == null)
			list = service.findAll();
		else
			list = service.findAll(search);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Especialidade> findById(@PathVariable Long id) {
        Especialidade obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
	public ResponseEntity<Especialidade> insert(@RequestBody Especialidade obj) {
		Especialidade entity = service.insert(obj);
		
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
	public ResponseEntity<Especialidade> update(@PathVariable Long id, @RequestBody Especialidade obj) {
		obj = service.update(id, obj);
		
		return ResponseEntity.ok().body(obj);
	}
}
