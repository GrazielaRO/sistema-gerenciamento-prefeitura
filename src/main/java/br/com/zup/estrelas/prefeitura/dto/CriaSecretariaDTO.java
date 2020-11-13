package br.com.zup.estrelas.prefeitura.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.zup.estrelas.prefeitura.enums.AreaAtuacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CriaSecretariaDTO {
	
	@Enumerated(EnumType.STRING)
	private AreaAtuacao areaAtuacao;
	
	private Double orcamentoProjeto;
	
	private Double orcamentoFolha;
	
	private String telefone;
	
	private String endereco;
	
	private String site;
	
	private String email;
	
}
