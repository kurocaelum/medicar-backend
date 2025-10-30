package com.kurocaelum.medicar.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String crm;
	private String nome;
	private String email;

	private Especialidade especialidade;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Agenda> agenda = new ArrayList<Agenda>();
	
	public Medico(Long id, String crm, String nome, String email) {
		super();
		this.id = id;
		this.crm = crm;
		this.nome = nome;
		this.email = email;
	}
	
	public Medico(Long id, String crm, String nome) {
		super();
		this.id = id;
		this.crm = crm;
		this.nome = nome;
		this.email = null;
	}
	
}
