package br.com.tartaro.repository.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:37:14
 *
 * Classe responsavel por persistir com nossa tabela usuario(tb_usuario) no Banco de Dados.
 */
 
@Table(name="tb_usuario") //tabela de usuario no Banco de Dados.
@Entity	
@NamedQuery(name = "UsuarioEntity.findUser", 
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha") //Query utilizada para fazer a autentificação de usuario e senha.
public class UsuarioEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue
	@Column(name="id_usuario")	//nome referente ao nome da coluna da tabela tb_usuario.
	private int codigo;
 
	@Column(name="ds_login")	//nome referente ao nome da coluna da tabela tb_usuario.
	private String usuario;
 
	@Column(name="ds_senha")	//nome referente ao nome da coluna da tabela tb_usuario.
	private String senha;
 
	/* Metodo resposavel por capturar o codigo que ira vir da coluna id_usuario.  */
	public int getCodigo() {	
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna id_usuario.  */
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/* Metodo resposavel por capturar o codigo que ira vir da coluna id_usuario.  */
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
 
}

