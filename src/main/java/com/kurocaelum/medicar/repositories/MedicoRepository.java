package com.kurocaelum.medicar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurocaelum.medicar.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
