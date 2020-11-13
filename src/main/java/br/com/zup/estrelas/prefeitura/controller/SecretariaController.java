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

import br.com.zup.estrelas.prefeitura.dto.AlteraSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.CriaSecretariaDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.service.ISecretariaService;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {
	
	@Autowired
	ISecretariaService secretariaService;
	
	@PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaSecretaria (@RequestBody CriaSecretariaDTO criaSecretariaDto) {
		return secretariaService.adicionaSecretaria(criaSecretariaDto);
	}
	
	@GetMapping (path = "/{idSecretaria}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Secretaria consultaSecretaria (@PathVariable Long idSecretaria) {
		return secretariaService.consultarSecretaria(idSecretaria);
	}
	
	@PutMapping (path = "/{idSecretaria}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO alteraSecretaria(@PathVariable Long idSecretaria, @RequestBody AlteraSecretariaDTO alteraSecretariaDTO) {
		return secretariaService.alteraSecretaria(idSecretaria, alteraSecretariaDTO);
	}
	
	@DeleteMapping (path = "/{idSecretaria}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO removeSecretaria (@PathVariable Long idSecretaria) {
		return secretariaService.removeSecretaria(idSecretaria);
	}
	
	@GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Secretaria> listaSecretarias(){
		return secretariaService.listaSecretarias();
	}

}
