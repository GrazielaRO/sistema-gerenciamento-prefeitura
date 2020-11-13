	package br.com.zup.estrelas.prefeitura.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDTO {
	
	private String nome;
	
	private String cpf;
	
	private Double salario;
	
	private Long idSecretaria;
	
	private String funcao;
	
	private Boolean concursado;

}
