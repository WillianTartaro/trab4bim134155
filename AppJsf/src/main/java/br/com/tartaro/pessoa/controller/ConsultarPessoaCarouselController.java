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
 * @author WillianTartaro - @date 17 de nov de 2016 - 13:32:34
 *
 *Classe resposavel por gerenciar a consulta de pessoas com carousel.
 */

@Named(value="consultarPessoaCarouselController")//Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@ViewScoped//escopo da sess�o
public class ConsultarPessoaCarouselController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient //pessoaRepository ira receber a inje��o de dependencias.
	private PessoaRepository pessoaRepository;
 
	@Produces //Quando invorcarmos uma inje��o, ele vai automaticamente usar esse.
	private List<PessoaModel> pessoas;
 
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}
 
	/*
	 * Atribui o valor a variavel.
	 */
	@PostConstruct //logo em seguida da cria��o do construtor da classe, ele ir� executar esse metodo em seguida.
	private void init(){
 
		this.pessoas = pessoaRepository.GetPessoas();
	}
 
 
 
 
}