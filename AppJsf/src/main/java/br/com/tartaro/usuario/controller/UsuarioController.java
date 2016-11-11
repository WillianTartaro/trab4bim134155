package br.com.tartaro.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.apache.commons.lang3.StringUtils;
 
import br.com.tartaro.model.UsuarioModel;
import br.com.tartaro.repository.UsuarioRepository;
import br.com.tartaro.repository.entity.UsuarioEntity;
import br.com.tartaro.uteis.Uteis;
 
/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:42:33
 *
 *
 */


@Named(value="usuarioController") //Quando usamos o @Named que pertence ao CDI, esta transformando a classe em um Bean.
@SessionScoped //escopo da sessão
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	@Inject //usuarioModel ira receber a injeção de dependencias.
	private UsuarioModel usuarioModel;
 
	@Inject //usuarioRepository ira receber a injeção de dependencias.
	private UsuarioRepository usuarioRepository;
 
	@Inject //usuarioEntity ira receber a injeção de dependencias.
	private UsuarioEntity usuarioEntity;
 
	/* ira capturar informações do UsuarioModel.*/
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	/* Metodo que retorna o usuario logado no sistema. */ 
	public UsuarioModel GetUsuarioSession(){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		return (UsuarioModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
	
	/* Metodo que finaliza a conexão do usuario(logout) e o redireciona para a pagina de login. */
	public String Logout(){
 
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 
		return "/index.xhtml?faces-redirect=true";
	}
	
	/* Metodo que realiza a autentificação do usuario/senha.*/
	public String EfetuarLogin(){
 
		if(StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())){
			//caso o campo usuario estiver em branco, exibi a mensagem
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())){
			//caso o campo senha estiver em branco, exibi a mensagem
			Uteis.Mensagem("Favor informar a senha!");
			return null;
		}
		else{	
 
			usuarioEntity = usuarioRepository.ValidaUsuario(usuarioModel);
 
			if(usuarioEntity!= null){
 
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());
 
 
				FacesContext facesContext = FacesContext.getCurrentInstance();
 
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
 
				//redireciona para a principal.
				return "sistema/home?faces-redirect=true";
			}
			else{
				//exibi a mensagem.
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
 
 
	}
}
