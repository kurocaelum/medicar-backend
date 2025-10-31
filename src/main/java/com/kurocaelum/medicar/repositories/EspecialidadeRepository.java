package com.kurocaelum.medicar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurocaelum.medicar.entities.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	boolean existsByNome(String nome);
}
