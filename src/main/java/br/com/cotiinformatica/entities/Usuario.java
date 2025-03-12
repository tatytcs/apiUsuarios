package br.com.cotiinformatica.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_usuario")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
	
	//Informa que o atributo é uma chave primária
	@Id
	@Column(name = "id")
	private UUID id;
	
	//nullable = false -> campo obrigatório
	@Column(name = "nome" ,length = 150, nullable = false)
	private String nome;
	
	//unique = true -> campo com valores únicos
	@Column(name = "email", length = 150, nullable = false, unique = true)
	private String email;
	
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	//Muitos usuários para um perfil
	@ManyToOne
	
	//chave estrangeira na tabela de usuários
    @JoinColumn(name = "perfil_id", nullable = false)	
	private Perfil perfil;

}
