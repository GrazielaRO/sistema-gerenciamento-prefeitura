package br.com.zup.estrelas.prefeitura.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum AreaAtuacao {
	
	SAUDE("saude"), TRANSITO ("transito"), TRANSPORTE("transporte"), EDUCACAO("EDUCACAO");
	
	private String value;

}
