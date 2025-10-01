package com.kurocaelum.medicar.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dia;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
	private List<Consulta> horarios = new ArrayList<Consulta>();
	
	public Agenda(Long id, Medico medico, LocalDate dia) {
		super();
		this.id = id;
		this.medico = medico;
		this.dia = dia;
	}

}
