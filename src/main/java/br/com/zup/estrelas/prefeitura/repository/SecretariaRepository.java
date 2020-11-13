package br.com.zup.estrelas.prefeitura.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.prefeitura.entity.Secretaria;
import br.com.zup.estrelas.prefeitura.enums.AreaAtuacao;

@Repository
public interface SecretariaRepository extends CrudRepository<Secretaria, Long>{

	Optional<Secretaria> findByAreaAtuacao(AreaAtuacao areaAtuacao);
	
}
