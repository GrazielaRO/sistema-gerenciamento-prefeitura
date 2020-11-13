package br.com.zup.estrelas.prefeitura.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProjetoDTO {
	
	private String nome;
	
	private String descricao;
	
	private Double custo;
	
	private Long idSecretaria;
	
	private LocalDate dataInicio;

}
