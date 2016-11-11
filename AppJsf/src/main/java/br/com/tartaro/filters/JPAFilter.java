package br.com.tartaro.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author WillianTartaro - @date 11 de nov de 2016 - 09:33:01
 *
 * Esse filter, vai ser executado toda vez que alguem fizer uma requisi��o Faces Servlet.
 * Foi criado filter, pois � necessario que as requisi��es sejam filtradas.
 * Ele tambem vai gerenciar o EntityManager, que no caso, � onde vai ser executada nossas acoes no Banco.
 * 
 * */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {
 
 
	private EntityManagerFactory entityManagerFactory;
 
	private String persistence_unit_name = "unit_app";
 
    public JPAFilter() {
 
    }
    /*
     * Esse metodo ao ser chamado, ir� realizar o fechamento do EntityManager
     */
	public void destroy() {
 
		this.entityManagerFactory.close();
	}
	
	
	/*
	 * Metodo responsavel pela cria��o do EntityManager.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		/*Criando o entityManager, com as informa��es.*/
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
 
		/*Apos a cria��o do entityManager, ele � adicionado na requisi��o.*/
		request.setAttribute("entityManager", entityManager);
 
		/*Inicia a transa��o com o banco*/
		entityManager.getTransaction().begin();
 
		/*Iniciando o Face Servlet.*/
		chain.doFilter(request, response);
 
		try {
 
			/*Se ap�s a execu��o dos procedimentos acima nao houver erro, entityManager gera o commit.*/
			entityManager.getTransaction().commit();
 
		} catch (Exception e) {
 
			/*Se ap�s a execu��o dos procedimentos acima houver erro, entityManager gera o rollback.*/
			entityManager.getTransaction().rollback();
		}
		finally{
 
			/*Ap�s a execu��o do commit ou rollback, entityManager � encerrado */
			entityManager.close();
		}
	}
 
	/*
	 * Metodo responsavel pela cria��o do entityManagerFactory.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		/* cria��o do entityManagerFactory com base na persistence.xml*/
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
	}
 
}