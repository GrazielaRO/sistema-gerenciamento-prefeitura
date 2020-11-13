package br.com.zup.estrelas.prefeitura.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@Column (name = "id_funcionario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private Double salario;
	
	@ManyToOne
	@JoinColumn(name = "id_secretaria", nullable = false)
	@JsonBackReference
	private Secretaria secretaria;
	
	@Column(nullable = false)
	private String funcao;
	
	@Column(nullable = false)
	private Boolean concursado;
	
	@Column(name = "data_admissao", nullable = false)
	private LocalDate dataAdmissao;

}
