package br.com.tartaro.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.model.UsuarioModel;
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
	
	/*
	 * Lista com todos os cadastros de pessoas no Banco de Dados.
	 */
	public List<PessoaModel> GetPessoas(){
 
		//Cria Lista.
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
 
		//Cria a variavel.
		entityManager =  Uteis.JpaEntityManager();
 
		//Query usada para fazer a consulta no banco.
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
 
		PessoaModel pessoaModel = null;
 
		//Percorre a lista.
		for (PessoaEntity pessoaEntity : pessoasEntity) {
 
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
 
			//Altera o tipo de origem.
			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");
 
			
			if(pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");
 
			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();			
 
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
 
			pessoaModel.setUsuarioModel(usuarioModel);
 
			//Adiciona pessoaModel a lista.
			pessoasModel.add(pessoaModel);
		}
 
		return pessoasModel;
 
	}
	
	/*
	 * Consulta uma pessoa pelo codigo.
	 * codigo recebe as informações da pessoa.
	 */
	private PessoaEntity GetPessoa(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(PessoaEntity.class, codigo);
	}
 
	/*
	 * Altera o resgitro(pessoa) no banco de dados.
	 * pessoaModel Contem as informações da pessoa editada.
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());
 
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());
 
		entityManager.merge(pessoaEntity);
	}
	
	/*
	 * Metodo responsavel por excluir o registro
	 * codigo contem as informações de qual registro é para ser excluido.
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		PessoaEntity pessoaEntity = this.GetPessoa(codigo);
 
		entityManager.remove(pessoaEntity);
	}
}
