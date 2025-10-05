package com.kurocaelum.medicar.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.entities.Medico;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	Agenda findByDiaAndMedico(LocalDate dia, Medico medico); 
}
