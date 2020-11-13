package br.com.zup.estrelas.prefeitura.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.zup.estrelas.prefeitura.enums.AreaAtuacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "secretaria")
public class Secretaria {

	@Id
    @Column(name = "id_secretaria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSecretaria;
	
	@Column(name = "area_atuacao")
    @Enumerated(EnumType.STRING)
	private AreaAtuacao areaAtuacao;
	
	@Column (name = "orcamento_projeto", nullable = false)
	private Double orcamentoProjeto;
	
	@Column (name = "orcamento_folha", nullable = false)
	private Double orcamentoFolha;
	
	@Size (max = 15)
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String site;

	@Email
	@Size (max = 255)
	@Column(nullable = false)
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "secretaria")
	@JsonManagedReference
	private List<Funcionario> funcionarios;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "secretaria")
	@JsonManagedReference
	private List<Projeto> projetos;
	
}
