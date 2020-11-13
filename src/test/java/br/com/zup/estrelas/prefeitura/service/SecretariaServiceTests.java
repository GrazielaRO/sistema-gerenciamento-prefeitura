package br.com.zup.estrelas.prefeitura.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.prefeitura.dto.CriaSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.enums.AreaAtuacao;
import br.com.zup.estrelas.prefeitura.repository.SecretariaRepository;

@RunWith(MockitoJUnitRunner.class)
public class SecretariaServiceTests {

	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Secretaria removida com sucesso";
	private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso!";
	private static final String SECRETARIA_JÁ_CADASTRADA = "Secretaria já cadastrada!";
	private static final String SECRETARIA_INEXISTENTE = "Secretaria inexistente.";

	@Mock
	SecretariaRepository secretariaRepository;

	@InjectMocks
	SecretariaService secretariaService;

	@Test
	public void deveAdicionarSecretaria() {

		CriaSecretariaDTO secretariaDto = criaSecretariaDTO();

		Mockito.when(secretariaRepository.findByAreaAtuacao(AreaAtuacao.SAUDE)).thenReturn(Optional.empty());

		MensagemDTO mensagemRetornada = this.secretariaService.adicionaSecretaria(secretariaDto);
		MensagemDTO mensagemEsperada = new MensagemDTO(CADASTRO_REALIZADO_COM_SUCESSO);

		Assert.assertEquals("Deve retornar mensagem de sucesso no cadastro", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void naoDeveAdicionarSecretaria() {
		
		CriaSecretariaDTO secretariaDto = criaSecretariaDTO();
			
		Secretaria secretaria = criaSecretaria();
		
		Mockito.when(secretariaRepository.findByAreaAtuacao(AreaAtuacao.SAUDE)).thenReturn(Optional.of(secretaria));

		MensagemDTO mensagemRetornada = this.secretariaService.adicionaSecretaria(secretariaDto);
		MensagemDTO mensagemEsperada = new MensagemDTO(SECRETARIA_JÁ_CADASTRADA);

		Assert.assertEquals("Deve retornar mensagem de insucesso no cadastro", mensagemEsperada, mensagemRetornada);

	}

	@Test
	public void deveRemoverUmaSecretaria() {
				
		Mockito.when(secretariaRepository.existsById(1L)).thenReturn(true);

		MensagemDTO mensagemRetornada = this.secretariaService.removeSecretaria(1L);
		MensagemDTO mensagemEsperada = new MensagemDTO(SECRETARIA_REMOVIDA_COM_SUCESSO);

		Assert.assertEquals("Deve retornar mensagem de sucesso na remoção da secretaria", mensagemEsperada, mensagemRetornada);

	}

	@Test
	public void naoDeveRemoverUmaSecretaria() {

		Mockito.when(secretariaRepository.findById(1L)).thenReturn(Optional.empty());

		MensagemDTO mensagemRetornada = this.secretariaService.removeSecretaria(1L);
		MensagemDTO mensagemEsperada = new MensagemDTO(SECRETARIA_INEXISTENTE);

		Assert.assertEquals("Deve retornar mensagem insucesso na remoção da secretaria", mensagemEsperada,
				mensagemRetornada);
	}
	
	@Test
	public void deveConsultarSecretaria() {
		
		Secretaria secretaria = criaSecretaria();
		
		Mockito.when(secretariaRepository.findById(1L)).thenReturn(Optional.of(secretaria));
		
		Assert.assertEquals(secretaria, this.secretariaService.consultarSecretaria(1L));
	}
	
	@Test
	public void naoDeveConsultarSecretaria() {

		Mockito.when(secretariaRepository.findById(1L)).thenReturn(Optional.empty());

		Assert.assertEquals(null, this.secretariaService.consultarSecretaria(1L));
	}

	static CriaSecretariaDTO criaSecretariaDTO() {

		CriaSecretariaDTO secretariaDto = new CriaSecretariaDTO();

		secretariaDto.setAreaAtuacao(AreaAtuacao.SAUDE);
		secretariaDto.setOrcamentoProjeto(1000.0);
		secretariaDto.setOrcamentoFolha(1000.0);
		secretariaDto.setTelefone("34965847562");
		secretariaDto.setEndereco("Rua das Ostras, 123");
		secretariaDto.setSite("www.msmg.gov.br");
		secretariaDto.setEmail("msmg@gov.br");

		return secretariaDto;
	}

	static Secretaria criaSecretaria() {

		Secretaria secretaria = new Secretaria();
		
		secretaria.setIdSecretaria(1L);
		secretaria.setAreaAtuacao(AreaAtuacao.SAUDE);
		secretaria.setOrcamentoProjeto(1000.0);
		secretaria.setOrcamentoFolha(1000.0);
		secretaria.setTelefone("34965847562");
		secretaria.setEndereco("Rua das Ostras, 123");
		secretaria.setSite("www.msmg.gov.br");
		secretaria.setEmail("msmg@gov.br");

		return secretaria;
	}

}
