package br.com.tartaro.repository;

import java.io.Serializable;

import javax.persistence.Query;
 
import br.com.tartaro.model.UsuarioModel;
import br.com.tartaro.repository.entity.UsuarioEntity;
import br.com.tartaro.uteis.Uteis;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:40:39
 *
 *Classe que vai fazer a busca no Banco de Dados, atravez das informações, Usuario e senha.
 */

public class UsuarioRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	/* Metodo que irá validar o usuario e senha, atraves da consulta.
	 * O metodo ira receber o "usuarioModel" contendo o usuario e a senha que é para ser encontrado. */
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
 
		try {
 
			//Query que ira ser executada para procurar o usuario e senha.	
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
 
			//Informações do resultado da pesquisa no Banco.
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
 
			//Se o usuario for encontrado, ele ira retornar o resultado.
			return (UsuarioEntity)query.getSingleResult();
 
		} catch (Exception e) {
 
			return null;
		}
 
 
 
	}
}
