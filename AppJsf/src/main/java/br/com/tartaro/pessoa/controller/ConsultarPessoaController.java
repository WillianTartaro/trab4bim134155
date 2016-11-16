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
 
	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct //logo em seguida da criação do construtor da classe, ele irá executar esse metodo em seguida.
	public void init(){
 
		//Retorna as pessoas cadastradas.
		this.pessoas = pessoaRepository.GetPessoas();
	}
 
 
}