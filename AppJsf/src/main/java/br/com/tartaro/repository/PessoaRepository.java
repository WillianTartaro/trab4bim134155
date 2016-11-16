package br.com.tartaro.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
 
import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.repository.entity.PessoaEntity;
import br.com.tartaro.repository.entity.UsuarioEntity;
import br.com.tartaro.uteis.Uteis;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 17:22:45
 *
 *Classe responsavel por persistir a entidade PessoaEntity.
 */

public class PessoaRepository {
	 
	@Inject //pessoaEntity ira receber a injeção de dependencias.
	PessoaEntity pessoaEntity;
 
	EntityManager entityManager;
 
	/* Metodo responsavel por salvar uma pessoa. */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());
 
		/* Faz uma busca pelo usuario, que esteja vinculado a pessoa. */
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); 
 
		pessoaEntity.setUsuarioEntity(usuarioEntity);
 
		
		entityManager.persist(pessoaEntity);
 
	}
}
