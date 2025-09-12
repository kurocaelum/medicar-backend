package com.kurocaelum.medicar.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String crm;
	private String nome;
	private String email;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	private List<Agenda> agenda = new ArrayList<Agenda>();
	
	public Medico(){}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(id, other.id);
	}
	
}
