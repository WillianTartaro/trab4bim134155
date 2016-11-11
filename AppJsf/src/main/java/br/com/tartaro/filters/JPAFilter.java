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
 * Esse filter, vai ser executado toda vez que alguem fizer uma requisição Faces Servlet.
 * Foi criado filter, pois é necessario que as requisições sejam filtradas.
 * Ele tambem vai gerenciar o EntityManager, que no caso, é onde vai ser executada nossas acoes no Banco.
 * 
 * */
@WebFilter(servletNames ={ "Faces Servlet" })
public class JPAFilter implements Filter {
 
 
	private EntityManagerFactory entityManagerFactory;
 
	private String persistence_unit_name = "unit_app";
 
    public JPAFilter() {
 
    }
    /*
     * Esse metodo ao ser chamado, irá realizar o fechamento do EntityManager
     */
	public void destroy() {
 
		this.entityManagerFactory.close();
	}
	
	
	/*
	 * Metodo responsavel pela criação do EntityManager.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		/*Criando o entityManager, com as informações.*/
		EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
 
		/*Apos a criação do entityManager, ele é adicionado na requisição.*/
		request.setAttribute("entityManager", entityManager);
 
		/*Inicia a transação com o banco*/
		entityManager.getTransaction().begin();
 
		/*Iniciando o Face Servlet.*/
		chain.doFilter(request, response);
 
		try {
 
			/*Se após a execução dos procedimentos acima nao houver erro, entityManager gera o commit.*/
			entityManager.getTransaction().commit();
 
		} catch (Exception e) {
 
			/*Se após a execução dos procedimentos acima houver erro, entityManager gera o rollback.*/
			entityManager.getTransaction().rollback();
		}
		finally{
 
			/*Após a execução do commit ou rollback, entityManager é encerrado */
			entityManager.close();
		}
	}
 
	/*
	 * Metodo responsavel pela criação do entityManagerFactory.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		/* criação do entityManagerFactory com base na persistence.xml*/
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
	}
 
}