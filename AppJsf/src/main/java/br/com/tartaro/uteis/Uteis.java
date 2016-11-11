package br.com.tartaro.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:36:04
 *
 *Essa classe vai agregar os metodos que irão recuperar o EntityManger criado no JPAFilter.
 *
 */

public class Uteis {

	/*Metodo usado para fazer a recuperação do EntityManger.
	 * Retornando o EntityManger. */
	public static EntityManager JpaEntityManager(){
		 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		ExternalContext externalContext = facesContext.getExternalContext();
 
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
 
		return (EntityManager)request.getAttribute("entityManager");
	} 
	
	/*Metodo utilizado para mostrar uma mensagem na tela.
	 *@param mensagem é a mensagem que ira ser apresentado na tela. */
		public static void Mensagem(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			/* adiciona a mensagem no facesContext e mostra na tela */
			facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
		}
	 
		/*Metodo utilizado para mostrar uma mensagem na tela.
		 * @param mensagem é a mensagem que ira ser apresentado na tela.*/
		public static void MensagemAtencao(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			/* adiciona a mensagem no facesContext e mostra na tela */
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
		}
	 
		/*Metodo utilizado para mostrar uma mensagem na tela.
		 * @param mensagem é a mensagem que ira ser apresentado na tela.*/
		public static void MensagemInfo(String mensagem){
	 
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			/* adiciona a mensagem no facesContext e mostra na tela */
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
		}
}
