package br.com.zup.estrelas.prefeitura.service;

import java.util.List;

import br.com.zup.estrelas.prefeitura.dto.FuncionarioDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Funcionario;

public interface IFuncionarioService {
	
	public MensagemDTO adicionaFuncionario (FuncionarioDTO adicionaFuncionarioDto);
	
	public Funcionario consultaFuncionario (Long idFuncionario);
	
	public MensagemDTO alteraFuncionario (Long idFuncionario, FuncionarioDTO alteraFuncionarioDto);
		
	public MensagemDTO removeFuncionario (Long idFuncionario);
	
	public List<Funcionario> listaFuncionarios();
	
}
