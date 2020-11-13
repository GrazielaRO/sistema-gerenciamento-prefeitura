package br.com.zup.estrelas.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.prefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.prefeitura.dto.DataFinalProjetoDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.dto.ProjetoDTO;
import br.com.zup.estrelas.prefeitura.entity.Projeto;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.repository.ProjetoRepository;
import br.com.zup.estrelas.prefeitura.repository.SecretariaRepository;
 
@Service
public class ProjetoService implements IProjetoService{
	
	private static final String DESCRICAO_ALTERADA_COM_SUCESSO = "Descrição alterada com sucesso!";
	private static final String DATA_FINAL_ANTERIOR_A_DATA_DE_INICIO = "A data final não pode ser anterior à data de início do projeto.";
	private static final String ORCAMENTO_PARA_PROJETOS_INSUFICIENTE = "Orçamento para projetos insuficiente!";
	private static final String PROJETO_CADASTRADO_COM_SUCESSO = "Projeto cadastrado com sucesso!";
	private static final String POJETO_JA_CADASTRADO = "Pojeto já cadastrado!";
	private static final String DATA_FINAL_ALTERADA_COM_SUCESSO = "Data final alterada com sucesso!";
	private static final String PROJETO_INEXISTENTE = "Projeto inexistente";
	
	@Autowired
	ProjetoRepository projetoRepository;
	
	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaProjeto(ProjetoDTO projetoDto) {
		
		if (projetoRepository.findByNome(projetoDto.getNome()).isPresent()) {
			return new MensagemDTO(POJETO_JA_CADASTRADO);
		}

		Optional<Secretaria> secretariaConsultada = secretariaRepository.findById(projetoDto.getIdSecretaria());
		Secretaria secretaria = secretariaConsultada.get();
		
		if (secretaria.getOrcamentoProjeto() < projetoDto.getCusto()) {
			return new MensagemDTO(ORCAMENTO_PARA_PROJETOS_INSUFICIENTE);
		}
		
		Projeto projeto = new Projeto();
		BeanUtils.copyProperties(projetoDto, projeto);
		projeto.setDataEntrega(null);
		projeto.setSecretaria(secretaria);
		projetoRepository.save(projeto);
		
		secretaria.setOrcamentoProjeto(secretaria.getOrcamentoProjeto() - projetoDto.getCusto());
		secretariaRepository.save(secretaria);
		
		return new MensagemDTO(PROJETO_CADASTRADO_COM_SUCESSO);
	}

	public Projeto consultaProjeto(Long idProjeto) {
			return projetoRepository.findById(idProjeto).orElse(null);
	}

	public MensagemDTO alteraProjeto(Long idProjeto, AlteraProjetoDTO alteraprojetoDto) {
		
		Optional<Projeto> projetoConsultado = projetoRepository.findById(idProjeto);
		
		if (projetoConsultado.isEmpty()) {
			return new MensagemDTO (PROJETO_INEXISTENTE);
		}
		
		Projeto projetoAlterado = projetoConsultado.get();
		
		projetoAlterado.setDescricao(alteraprojetoDto.getDescricao());
		
		projetoRepository.save(projetoAlterado);
		
		return new MensagemDTO (DESCRICAO_ALTERADA_COM_SUCESSO);
	}

	public List<Projeto> listaProjetos() {
		return (List<Projeto>) projetoRepository.findAll();
	}

	public MensagemDTO finalizaProjeto(Long idProjeto, DataFinalProjetoDTO dataFinal) {
		
		Optional <Projeto> projetoConsultado = projetoRepository.findById(idProjeto);
		
		if (projetoConsultado.isEmpty()) {
			return new MensagemDTO (PROJETO_INEXISTENTE);
		}
		
		Projeto projeto = projetoConsultado.get();
		
		if (projeto.getDataInicio().isAfter(dataFinal.getDataEntrega())) {
			return new MensagemDTO (DATA_FINAL_ANTERIOR_A_DATA_DE_INICIO);
		}
		
		projeto.setDataEntrega(dataFinal.getDataEntrega());
		projeto.setConcluido(true);
		
		projetoRepository.save(projeto);
		
		return new MensagemDTO (DATA_FINAL_ALTERADA_COM_SUCESSO);
	}

}
