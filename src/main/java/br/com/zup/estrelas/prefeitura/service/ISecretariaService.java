package br.com.zup.estrelas.prefeitura.service;

import java.util.List;

import br.com.zup.estrelas.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.CriaSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;

public interface ISecretariaService {
	
	public MensagemDTO adicionaSecretaria(CriaSecretariaDTO criaSecretariaDto);
	
	public Secretaria consultarSecretaria (Long idSecretaria);
	
	public MensagemDTO alteraSecretaria(Long idSecretaria, AlteraSecretariaDTO alteraSecretariaDTO);
	
	public MensagemDTO removeSecretaria (Long idSecretaria);
	
	public List<Secretaria> listaSecretarias();

}
