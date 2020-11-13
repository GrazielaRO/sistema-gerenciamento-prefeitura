package br.com.zup.estrelas.prefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.CriaSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.repository.SecretariaRepository;

@Service
public class SecretariaService implements ISecretariaService {

	private static final String ALTERACAO_REALIZADA_COM_SUCESSO = "Alteração realizada com sucesso!";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso!";
	private static final String SECRETARIA_JÁ_CADASTRADA = "Secretaria já cadastrada!";
	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Secretaria removida com sucesso";
	private static final String SECRETARIA_INEXISTENTE = "Secretaria inexistente.";

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaSecretaria(CriaSecretariaDTO criaSecretariaDto) {

		if (secretariaRepository.findByAreaAtuacao(criaSecretariaDto.getAreaAtuacao()).isPresent()) {

			return new MensagemDTO(SECRETARIA_JÁ_CADASTRADA);
		}

		Secretaria secretaria = new Secretaria();

		BeanUtils.copyProperties(criaSecretariaDto, secretaria);

		secretariaRepository.save(secretaria);

		return new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);
	}

	public Secretaria consultarSecretaria(Long idSecretaria) {
		return secretariaRepository.findById(idSecretaria).orElse(null);
	}

	public MensagemDTO alteraSecretaria(Long idSecretaria, AlteraSecretariaDTO alteraSecretariaDTO) {

		Optional<Secretaria> secretariaConsultada = secretariaRepository.findById(idSecretaria);

		if (secretariaConsultada.isEmpty()) {
			return new MensagemDTO(SECRETARIA_INEXISTENTE);
		}

		Secretaria secretariaAlterada = secretariaConsultada.get();

		secretariaAlterada.setAreaAtuacao(alteraSecretariaDTO.getAreaAtuacao());
		secretariaAlterada.setOrcamentoProjeto(alteraSecretariaDTO.getOrcamentoProjeto());
		secretariaAlterada.setOrcamentoFolha(alteraSecretariaDTO.getOrcamentoFolha());
		secretariaAlterada.setTelefone(alteraSecretariaDTO.getTelefone());
		secretariaAlterada.setEndereco(alteraSecretariaDTO.getEndereco());
		secretariaAlterada.setSite(alteraSecretariaDTO.getSite());
		secretariaAlterada.setEmail(alteraSecretariaDTO.getEmail());

		secretariaRepository.save(secretariaAlterada);

		return new MensagemDTO(ALTERACAO_REALIZADA_COM_SUCESSO);
	}

	public MensagemDTO removeSecretaria(Long idSecretaria) {

		if (!secretariaRepository.existsById(idSecretaria)) {

			return new MensagemDTO(SECRETARIA_INEXISTENTE);
		}

		secretariaRepository.deleteById(idSecretaria);

		return new MensagemDTO(SECRETARIA_REMOVIDA_COM_SUCESSO);
	}

	public List<Secretaria> listaSecretarias() {
		return (List<Secretaria>) secretariaRepository.findAll();
	}

}
