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
@Table(name = "projeto")
public class Projeto {
	
	@Id
	@Column (name = "id_projeto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjeto;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Double custo;
	
	@ManyToOne
	@JoinColumn(name = "id_secretaria", nullable = false)
	@JsonBackReference
	private Secretaria secretaria;
	
	@Column(name = "data_inicio", nullable = false)
	private LocalDate dataInicio;
	
	@Column (name = "data_entrega")
	private LocalDate dataEntrega;
	
	private Boolean concluido = false;

}
