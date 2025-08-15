package com.kurocaelum.medicar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurocaelum.medicar.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
