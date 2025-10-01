package com.kurocaelum.medicar.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalTime horario;
	private LocalDateTime dataAgendamento = null;
	
	@ManyToOne
	@JoinColumn(name = "agenda_id")
	@JsonIgnore
	private Agenda agenda;
	
	public Consulta(Long id, Agenda agenda, LocalTime horario) {
		super();
		this.id = id;
		this.agenda = agenda;
		this.horario = horario;
	}

	public Consulta(Long id, LocalTime horario, LocalDateTime dataAgendamento, Agenda agenda) {
		super();
		this.id = id;
		this.horario = horario;
		this.dataAgendamento = dataAgendamento;
		this.agenda = agenda;
	}

}
