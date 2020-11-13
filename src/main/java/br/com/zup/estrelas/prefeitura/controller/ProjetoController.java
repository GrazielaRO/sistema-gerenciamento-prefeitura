package br.com.zup.estrelas.prefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.prefeitura.dto.AlteraProjetoDTO;
import br.com.zup.estrelas.prefeitura.dto.DataFinalProjetoDTO;
import br.com.zup.estrelas.prefeitura.dto.MensagemDTO;
import br.com.zup.estrelas.prefeitura.dto.ProjetoDTO;
import br.com.zup.estrelas.prefeitura.entity.Projeto;
import br.com.zup.estrelas.prefeitura.service.IProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
	
	@Autowired
	IProjetoService projetoService;
	
	@PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO adicionaProjeto (@RequestBody ProjetoDTO projetoDto) {
		return projetoService.adicionaProjeto(projetoDto);
	}
	
	@GetMapping (path = "/{idProjeto}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Projeto consultaProjeto (@PathVariable Long idProjeto) {
		return projetoService.consultaProjeto(idProjeto);
	}
	
	@PutMapping (path = "/{idProjeto}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO alteraProjeto (@PathVariable Long idProjeto, @RequestBody AlteraProjetoDTO alterarojetoDto) {
		return projetoService.alteraProjeto(idProjeto, alterarojetoDto);
	}
	
	@GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Projeto> listaProjetos(){
		return projetoService.listaProjetos();
	}
	
	@PutMapping (path = "/conclusao/{idProjeto}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public MensagemDTO finalizaProjeto (@PathVariable Long idProjeto, @RequestBody DataFinalProjetoDTO dataFinalProjetoDto) {
		return projetoService.finalizaProjeto(idProjeto, dataFinalProjetoDto);
	}

}
