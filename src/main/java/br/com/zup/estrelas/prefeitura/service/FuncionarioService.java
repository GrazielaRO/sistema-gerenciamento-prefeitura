package br.com.zup.estrelas.prefeitura.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.prefeitura.dto.FuncionarioDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Funcionario;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.repository.FuncionarioRepository;
import br.com.zup.estrelas.prefeitura.repository.SecretariaRepository;

@Service
public class FuncionarioService implements IFuncionarioService {

	private static final String SECRETARIA_INEXISTENTE = "A secretaria informada não existe.";
	private static final String ALTERACAO_REALIZADA_COM_SUCESSO = "Alteração Realizada com sucesso!";
	private static final String ORCAMENTO_FOLHA_PAGAMENTO_INSUFICIENTE = "Orçamento de folha de pagamento insuficiente!";
	private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "Funcionário cadastrado com sucesso!";
	private static final String FUNCIONARIO_JA_CADASTRADO = "Funcionário já cadastrado!";
	private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "Funcionário removido com sucesso!";
	private static final String FUNCIONARIO_INEXISTENTE = "Funcionário inexistente!";

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	SecretariaRepository secretariaRepository;

	public MensagemDTO adicionaFuncionario(FuncionarioDTO funcionarioDto) {

		if (funcionarioRepository.findByCpf(funcionarioDto.getCpf()).isPresent()) {
			return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
		}

		Optional<Secretaria> secretariaConsultada = secretariaRepository.findById(funcionarioDto.getIdSecretaria());

		Secretaria secretaria = secretariaConsultada.get();

		if (secretaria.getOrcamentoFolha() < funcionarioDto.getSalario()) {
			return new MensagemDTO(ORCAMENTO_FOLHA_PAGAMENTO_INSUFICIENTE);
		}

		Funcionario funcionario = new Funcionario();

		BeanUtils.copyProperties(funcionarioDto, funcionario);

		funcionario.setDataAdmissao(LocalDate.now());
		funcionario.setSecretaria(secretaria);

		funcionarioRepository.save(funcionario);

		secretaria.setOrcamentoFolha(secretaria.getOrcamentoFolha() - funcionario.getSalario());

		secretariaRepository.save(secretaria);

		return new MensagemDTO(FUNCIONARIO_CADASTRADO_COM_SUCESSO);
	}

	public Funcionario consultaFuncionario(Long idFuncionario) {
		return funcionarioRepository.findById(idFuncionario).orElse(null);
	}

	public MensagemDTO alteraFuncionario(Long idFuncionario, FuncionarioDTO funcionarioDto) {

		Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

		if (funcionarioConsultado.isEmpty()) {
			return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
		}

		Funcionario funcionario = funcionarioConsultado.get();
		if (funcionarioDto.getIdSecretaria() != funcionario.getSecretaria().getIdSecretaria()) {

			Optional<Secretaria> secretariaConsultada = secretariaRepository.findById(funcionarioDto.getIdSecretaria());
			if (secretariaConsultada.isEmpty()) {
				return new MensagemDTO(SECRETARIA_INEXISTENTE);
			}

			Secretaria secretaria = secretariaConsultada.get();
			if (secretaria.getOrcamentoFolha() < funcionarioDto.getSalario()) {
				return new MensagemDTO(ORCAMENTO_FOLHA_PAGAMENTO_INSUFICIENTE);
			}

			Secretaria secretariaAntiga = funcionario.getSecretaria();
			secretariaAntiga.setOrcamentoFolha(secretariaAntiga.getOrcamentoFolha() + funcionario.getSalario());
			secretariaRepository.save(secretariaAntiga);

		}

		if (funcionarioDto.getSalario() >= funcionario.getSalario()) {

			if (funcionario.getSecretaria().getOrcamentoFolha() < funcionarioDto.getSalario()) {
				return new MensagemDTO(ORCAMENTO_FOLHA_PAGAMENTO_INSUFICIENTE);
			}

			Secretaria secretaria = secretariaRepository.findById(funcionarioDto.getIdSecretaria()).get();

			funcionario.setNome(funcionarioDto.getNome());
			funcionario.setCpf(funcionarioDto.getCpf());
			funcionario.setSalario(funcionarioDto.getSalario());
			funcionario.setSecretaria(secretaria);
			funcionario.setFuncao(funcionarioDto.getFuncao());
			funcionario.setConcursado(funcionarioDto.getConcursado());

			secretaria.setOrcamentoFolha(secretaria.getOrcamentoFolha() - funcionario.getSalario());
			secretariaRepository.save(secretaria);

			funcionarioRepository.save(funcionario);
		}

		return new MensagemDTO(ALTERACAO_REALIZADA_COM_SUCESSO);
	}

	public MensagemDTO removeFuncionario(Long idFuncionario) {

		if (!funcionarioRepository.existsById(idFuncionario)) {

			return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
		}

		Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
		Secretaria secretaria = secretariaRepository.findById(funcionario.getSecretaria().getIdSecretaria()).get();
		secretaria.setOrcamentoFolha(secretaria.getOrcamentoFolha() + funcionario.getSalario());
		secretariaRepository.save(secretaria);

		funcionarioRepository.deleteById(idFuncionario);

		return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
	}

	public List<Funcionario> listaFuncionarios() {
		return (List<Funcionario>) funcionarioRepository.findAll();
	}

}
