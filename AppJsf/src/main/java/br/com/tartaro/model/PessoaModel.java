package br.com.tartaro.model;

import java.time.LocalDateTime;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 17:21:19
 *
 *Classe usada pelo Managed Beans, adquiri as informações que sao utilizadas nas paginas.
 */



public class PessoaModel {
 
	private Integer 	codigo;
	private String  	nome;
	private String  	sexo;
	private LocalDateTime	dataCadastro;
	private String  	email;
	private String  	endereco;
	private String  	origemCadastro;
	private UsuarioModel    usuarioModel;
 
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getOrigemCadastro() {
		return origemCadastro;
	}
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
 
}