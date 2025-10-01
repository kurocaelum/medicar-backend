package com.kurocaelum.medicar.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String password;
	
	public User(Long id, String nome, String email, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

}
