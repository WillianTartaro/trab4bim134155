package br.com.tartaro.pessoa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.tartaro.model.PessoaModel;
import br.com.tartaro.repository.PessoaRepository;
import br.com.tartaro.usuario.controller.UsuarioController;
import br.com.tartaro.uteis.Uteis;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 17:24:16
 *
 *Classe que tem a responsabilidade de controlar os cadastros.
 */

@Named(value="cadastrarPessoaController")//Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@RequestScoped //escopo da sessão
public class CadastrarPessoaController {
 
	@Inject //pessoaModel ira receber a injeção de dependencias.
	PessoaModel pessoaModel;
 
	@Inject //usuusuarioControllerarioModel ira receber a injeção de dependencias.
	UsuarioController usuarioController;
 
	@Inject //pessoaRepository ira receber a injeção de dependencias.
	PessoaRepository pessoaRepository;
 
	/* ira capturar informações do UsuarioModel.*/
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
 
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/** 
	 *Metodo para salvar uma nova Pessoa
	 */
	public void SalvarNovaPessoa(){
 
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
		pessoaModel.setOrigemCadastro("I");
 
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
 
		this.pessoaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}