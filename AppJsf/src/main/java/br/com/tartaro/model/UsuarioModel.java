package br.com.tartaro.model;

import java.io.Serializable;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:39:03
 *
 *Classe usada pelo Managed Beans, adquiri as informações que sao utilizadas nas paginas.
 */

public class UsuarioModel implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 
	private String codigo;
	private String usuario;
	private String senha;
 
	
	/* Metodo resposavel por capturar o codigo que ira vir da pagina  */
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/* Metodo resposavel por capturar o usuario que ira vir da pagina  */
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/* Metodo resposavel por capturar o senha que ira vir da pagina  */
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
