package br.com.tartaro.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.repository.PessoaRepository;

/**
 * @author WillianTartaro - @date 16 de nov de 2016 - 09:13:32
 *
 *Classe resposanvel por gerenciar a consulta de pessoas.
 *
 */

@Named(value="consultarPessoaController") //Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@ViewScoped //escopo da sessão
public class ConsultarPessoaController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient //pessoaModel ira receber a injeção de dependencias.
	private PessoaModel pessoaModel;
 
	@Produces //Quando invorcarmos uma injeção, ele vai automaticamente usar esse.
	private List<PessoaModel> pessoas;
 
	@Inject transient //pessoaRepository ira receber a injeção de dependencias.
	private PessoaRepository pessoaRepository;
 
	/* ira capturar informações do List<PessoaModel> pessoas.*/
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}	
	
	/* ira capturar informações do pessoaModel.*/
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/*
	 * Carrega as pessoas na inicialização.
	 */
	@PostConstruct //logo em seguida da criação do construtor da classe, ele irá executar esse metodo em seguida.
	public void init(){
 
		//Retorna as pessoas cadastradas.
		this.pessoas = pessoaRepository.GetPessoas();
	}
	
	/*
	 * Carrega as informações de uma pessoa, para que possa ser editada.
	 * pessoaModel recebe as informações da pessoa a ser editada.
	 */
	public void Editar(PessoaModel pessoaModel){
 

		//Pega apenas a primeira letra do sexo (M ou F).
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
 
		this.pessoaModel = pessoaModel;
 
	}
 
	/*
	 * Atualiza o registro que foi editado.
	 */
	public void AlterarRegistro(){
 
		this.pessoaRepository.AlterarRegistro(this.pessoaModel);	
 
		this.init();//Faz a recarregar dos registros.
	}
	
	/*
	 * Exclui o registro do banco de dados.
	 * pessoaModel contem as informações do registro.
	 */
	public void ExcluirPessoa(PessoaModel pessoaModel){
 
		//exclui a pessoa do banco.
		this.pessoaRepository.ExcluirRegistro(pessoaModel.getCodigo());
 
		//Remove a pessoa da lista, assim que excluida, a lista é atualizada.
		this.pessoas.remove(pessoaModel);
 
	}
 
 
}