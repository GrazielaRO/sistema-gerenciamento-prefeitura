package br.com.zup.estrelas.prefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.prefeitura.dto.FuncionarioDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Funcionario;
import br.com.zup.estrelas.prefeitura.service.IFuncionarioService;

@RestController
@RequestMapping("/funcionarios")
//TODO: Grazi, aqui eu consigo compreender por quê tratou funcionarios
//como um recurso independente, mas dado que ele só pode pertencer à uma
//secretaria e isso não muda, seria interessante tratá-lo como um subrecurso
//de secretaria, seu endpoint seria algo como:
///secretarias/{id}/funcionarios. Dê uma olhada na referência do portal
//desenvolvimento para entender melhor por quê isso faz mas sentido como um subrecurso
//e qualquer dúvida pode falar comigo.
public class FuncionarioController {
	
	@Autowired
	IFuncionarioService funcionarioService;
	
	@PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaFuncionario (@RequestBody FuncionarioDTO adicionaFuncionarioDto) {
		return funcionarioService.adicionaFuncionario(adicionaFuncionarioDto);
	}
	
	@GetMapping (path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Funcionario consultaFuncionario (@PathVariable Long idFuncionario) {
		return funcionarioService.consultaFuncionario(idFuncionario);
	}
	
	@PutMapping (path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO alteraFuncionario (@PathVariable Long idFuncionario, @RequestBody FuncionarioDTO alteraFuncionarioDto) {
		return funcionarioService.alteraFuncionario(idFuncionario, alteraFuncionarioDto);
	}
	
	@DeleteMapping (path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO removeFuncionario (@PathVariable Long idFuncionario) {
		return funcionarioService.removeFuncionario(idFuncionario);
	}
	
	@GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Funcionario> listaFuncionarios(){
		return funcionarioService.listaFuncionarios();
	}

}
