package com.kurocaelum.medicar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurocaelum.medicar.entities.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	boolean existsByNome(String nome);

	List<Especialidade> findByNomeContainsIgnoreCase(String searchString);
}
